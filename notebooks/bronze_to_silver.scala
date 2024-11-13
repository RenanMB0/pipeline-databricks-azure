// Databricks notebook source
// MAGIC %python
// MAGIC dbutils.fs.ls("/mnt/dados/bronze")

// COMMAND ----------

// MAGIC %md
// MAGIC ## LENDO DADOS NA CAMADA BRONZE

// COMMAND ----------

val path = "dbfs:/mnt/dados/bronze/dataset_imoveis/"
val df = spark.read.format("delta").load(path)

// COMMAND ----------

display(df)

// COMMAND ----------

// MAGIC %md
// MAGIC ## TRANSFORMANDO OS CAMPOS DO JSON EM COLUNAS

// COMMAND ----------

display(df.select("anuncio.*"))

// COMMAND ----------

display(df.select("anuncio.*", "anuncio.endereco.*"))


// COMMAND ----------

val dados_detalhados = df.select("anuncio.*", "anuncio.endereco.*")

// COMMAND ----------

display(dados_detalhados)

// COMMAND ----------



// COMMAND ----------

// MAGIC %md
// MAGIC ## REMOVENDO COLUNAS

// COMMAND ----------

val df_silver = dados_detalhados.drop("caracteristicas", "endereco")
display(df_silver)

// COMMAND ----------

// MAGIC %md
// MAGIC ## SALVANDO NA CAMADA SILVER

// COMMAND ----------

val path = "dbfs:/mnt/dados/silver/dataset_imoveis"
df_silver.write.format("delta").mode("overwrite").save(path)

// COMMAND ----------


