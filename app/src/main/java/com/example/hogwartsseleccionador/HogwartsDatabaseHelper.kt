package com.example.hogwartsseleccionador

import AlumnoHogwarts
import FuncionesApoyo
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class HogwartsDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Hogwarts.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crear la tabla "alumnos"
        val createTableQuery = """
            CREATE TABLE alumnos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                familiaMaggle BOOL NOT NULL,
                apellido TEXT NOT NULL,
                casa TEXT NOT NULL,
                habilidad INTEGER,
                inteligencia INTEGER,
                creatividad INTEGER,
                etica INTEGER,
                coraje INTEGER,
                lealtad INTEGER
            )
        """.trimIndent()

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Eliminar la tabla "alumnos" si existe y volver a crearla
        //db.execSQL("DROP TABLE IF EXISTS alumnos")
        onCreate(db)
    }

    /**
     * Función insert donde añadimos un alumno completo
     */
    fun insert(alumno: AlumnoHogwarts): Long {
        return insert(
            alumno.nombre,
            alumno.familiaMaggle,
            alumno.casa,
            alumno.apellido,
            alumno.listaAtributos[0].valor,
            alumno.listaAtributos[1].valor,
            alumno.listaAtributos[2].valor,
            alumno.listaAtributos[3].valor,
            alumno.listaAtributos[4].valor,
            alumno.listaAtributos[5].valor
        )
    }

    private fun insert(
        nombre: String,
        familiaMaggle: Boolean,
        casa: String,
        apellido: String,
        habilidad: Int,
        inteligencia: Int,
        creatividad: Int,
        etica: Int,
        coraje: Int,
        lealtad: Int
    ): Long {
        val db = writableDatabase

        val values = ContentValues().apply {
            put("nombre", nombre)
            put("familiaMaggle", familiaMaggle)
            put("apellido", apellido)
            put("casa", casa)
            put("habilidad", habilidad)
            put("inteligencia", inteligencia)
            put("creatividad", creatividad)
            put("etica", etica)
            put("coraje", coraje)
            put("lealtad", lealtad)
        }

        val newRowId = db.insert("alumnos", null, values)
        db.close()
        return newRowId
    }

    // Método update()
    fun update(
        id: Int,
        nombre: String?,
        familiaMaggle: Boolean,
        casa: String?,
        apellido: String?,
        habilidad: Int?,
        inteligencia: Int?,
        creatividad: Int?,
        etica: Int?,
        coraje: Int?,
        lealtad: Int?
    ): Int {
        val db = writableDatabase

        val values = ContentValues().apply {
            if (nombre != null) put("nombre", nombre)
            if (familiaMaggle != null) put("familiaMaggle", familiaMaggle)
            if (apellido != null) put("apellido", apellido)
            if (casa != null) put("casa", casa)
            if (habilidad != null) put("habilidad", habilidad)
            if (inteligencia != null) put("inteligencia", inteligencia)
            if (creatividad != null) put("creatividad", creatividad)
            if (etica != null) put("etica", etica)
            if (coraje != null) put("coraje", coraje)
            if (lealtad != null) put("lealtad", lealtad)
        }

        val affectedRows = db.update("alumnos", values, "id = ?", arrayOf(id.toString()))
        db.close()
        return affectedRows
    }

    /**
     * Buscamos por idAlumno y nos devuelve un objeto de la clase AlumnoHogwarts
     */
    fun selectAlumno(idAlumno: Int): AlumnoHogwarts? {
        // Realizar una consulta SELECT con la condición para buscar el alumno específico
        val cursor = select("id = $idAlumno")
        // Procesar los resultados de la consulta
        cursor.use {
            if (it.moveToFirst()) {
                val nombre = it.getString(it.getColumnIndexOrThrow("nombre"))
                val familiaMaggle = it.getInt(it.getColumnIndexOrThrow("familiaMaggle"))
                val apellido = it.getString(it.getColumnIndexOrThrow("apellido"))
                val casa = it.getString(it.getColumnIndexOrThrow("casa"))
                val habilidad = it.getInt(it.getColumnIndexOrThrow("habilidad"))
                val inteligencia = it.getInt(it.getColumnIndexOrThrow("inteligencia"))
                val creatividad = it.getInt(it.getColumnIndexOrThrow("creatividad"))
                val etica = it.getInt(it.getColumnIndexOrThrow("etica"))
                val coraje = it.getInt(it.getColumnIndexOrThrow("coraje"))
                val lealtad = it.getInt(it.getColumnIndexOrThrow("lealtad"))

                val alumno = AlumnoHogwarts(
                    nombre,
                    FuncionesApoyo.integerToBoolean(familiaMaggle),
                    casa,
                    apellido,
                    habilidad,
                    inteligencia,
                    creatividad,
                    etica,
                    coraje,
                    lealtad
                )
                return alumno
            } else {
                return null;
            }
        }
    }

    /**
     * Obtenemos el último alumno introducido
     */
    fun ultimoAlumno(): AlumnoHogwarts?{
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM alumnos ORDER BY id DESC LIMIT 1"
        val cursor = db.rawQuery(selectQuery, null)

        cursor.use {
            if (it.moveToFirst()) {
                val nombre = it.getString(it.getColumnIndexOrThrow("nombre"))
                val familiaMaggle = it.getInt(it.getColumnIndexOrThrow("familiaMaggle"))
                val apellido = it.getString(it.getColumnIndexOrThrow("apellido"))
                val casa = it.getString(it.getColumnIndexOrThrow("casa"))
                val habilidad = it.getInt(it.getColumnIndexOrThrow("habilidad"))
                val inteligencia = it.getInt(it.getColumnIndexOrThrow("inteligencia"))
                val creatividad = it.getInt(it.getColumnIndexOrThrow("creatividad"))
                val etica = it.getInt(it.getColumnIndexOrThrow("etica"))
                val coraje = it.getInt(it.getColumnIndexOrThrow("coraje"))
                val lealtad = it.getInt(it.getColumnIndexOrThrow("lealtad"))

                val alumno = AlumnoHogwarts(
                    nombre,
                    FuncionesApoyo.integerToBoolean(familiaMaggle),
                    casa,
                    apellido,
                    habilidad,
                    inteligencia,
                    creatividad,
                    etica,
                    coraje,
                    lealtad
                )
                return alumno
            }
        }
        return null
    }

    private fun select(condition: String? = null): Cursor {
        val db = readableDatabase

        val projection = arrayOf(
            "id",
            "nombre",
            "apellido",
            "casa",
            "habilidad",
            "inteligencia",
            "creatividad",
            "etica",
            "coraje",
            "lealtad"
        )
        val cursor = if (condition == null) {
            db.query("alumnos", projection, null, null, null, null, null)
        } else {
            db.query("alumnos", projection, condition, null, null, null, null)
        }
        return cursor
    }

    // Método delete()
    fun delete(id: Int): Int {
        val db = writableDatabase
        val affectedRows = db.delete("alumnos", "id = ?", arrayOf(id.toString()))
        db.close()
        return affectedRows
    }
}