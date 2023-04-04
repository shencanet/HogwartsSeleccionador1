import kotlin.random.Random

/**
 * Clase para las casas de Hogwarts
 */
data class CasaHogwarts(private var nombreCasa: String) {
    /**
     * Forma similar para utilizar los métodos static de Java
     * OJO: usa el patrón Singlseton
     */
    companion object {
        //Constantes para los nombres de las casas
        val CASA_SLYTHERIN: String = "Slytherin"
        val CASA_GRIFFINDORF: String = "Griffindorf"
        val CASA_HUFFLEPLUFF: String = "Hufflepluff"
        val CASA_RAVENCLAW: String = "Ravenclaw"

        // Listados de apellidos según casas de Hogwarts
        private val slytherinApellidos: List<String> = listOf(
            "Riddle",
            "Malfoy",
            "Snape",
            "Lestrange",
            "Black",
            "Parkinson",
            "Zabini",
            "Sliughorn",
            "Nott",
            "Bulstrode"
        )
        private val griffindorfApellidos: List<String> = listOf(
            "Potter",
            "Granger",
            "Weasley",
            "Longbottom",
            "Finnigan",
            "Thomas",
            "Patil",
            "Brown",
            "McGonagall"
        )
        private val hufflepuffApellidos: List<String> = listOf(
            "Diggory",
            "Tonks",
            "Sprout",
            "Lestrange",
            "Abbott",
            "Macmillan",
            "Finch-Fletchley",
            "Bones",
            "Scamander",
            "Midgen"
        )
        private val ravenclawApellidos: List<String> = listOf(
            "Lovegood",
            "Chang",
            "Flitwick",
            "Patil",
            "Lockhart",
            "Myrtle",
            "Corner",
            "Boot",
            "Goldstein"
        )

        // Creamos las habilidades para cada casa => Mapa string -> valor
        val habilidadesSlytherin = mapOf<String, Int>(
            AtributoMagico.HABILIDAD to Random.nextInt(15, 20),
            AtributoMagico.INTELIGENCIA to Random.nextInt(20, 25),
            AtributoMagico.CREATIVIDAD to Random.nextInt(10, 25),
            AtributoMagico.ETICA to Random.nextInt(0, 10),
            AtributoMagico.CORAJE to Random.nextInt(5, 10),
            AtributoMagico.LEALTAD to Random.nextInt(10, 20)
        )
        val habilidadesGriffindorf = mapOf<String, Int>(
            AtributoMagico.HABILIDAD to Random.nextInt(10, 15),
            AtributoMagico.INTELIGENCIA to Random.nextInt(15, 20),
            AtributoMagico.CREATIVIDAD to Random.nextInt(10, 15),
            AtributoMagico.ETICA to Random.nextInt(10, 15),
            AtributoMagico.CORAJE to Random.nextInt(15, 30),
            AtributoMagico.LEALTAD to Random.nextInt(15, 20)
        )
        val habilidadesHufflepuff = mapOf<String, Int>(
            AtributoMagico.HABILIDAD to Random.nextInt(10, 15),
            AtributoMagico.INTELIGENCIA to Random.nextInt(10, 15),
            AtributoMagico.CREATIVIDAD to Random.nextInt(10, 15),
            AtributoMagico.ETICA to Random.nextInt(15, 20),
            AtributoMagico.CORAJE to Random.nextInt(10, 15),
            AtributoMagico.LEALTAD to Random.nextInt(15, 20)
        )
        val habilidadesRavenclaw = mapOf<String, Int>(
            AtributoMagico.HABILIDAD to Random.nextInt(10, 15),
            AtributoMagico.INTELIGENCIA to Random.nextInt(20, 25),
            AtributoMagico.CREATIVIDAD to Random.nextInt(20, 25),
            AtributoMagico.ETICA to Random.nextInt(10, 15),
            AtributoMagico.CORAJE to Random.nextInt(5, 10),
            AtributoMagico.LEALTAD to Random.nextInt(15, 25)
        )

        fun getApellido(casa: String): String {
            val listaApellidos: List<String>?
            // Elegimos el listado de apellidos correcto
            if (casa == CASA_SLYTHERIN) listaApellidos = slytherinApellidos;
            else if (casa == CASA_GRIFFINDORF) listaApellidos = griffindorfApellidos
            else if (casa == CASA_HUFFLEPLUFF) listaApellidos = hufflepuffApellidos
            else listaApellidos = ravenclawApellidos

            //Devolvemos un apellido al azar del listado de apellidos seleccionado
            val totalApellidos: Int = listaApellidos.size
            val posicion: Int = Random.nextInt(0, totalApellidos)

            return listaApellidos[posicion]
        }

        /**
         * Obtenemos un listados de atributos mágicos en función de cada casa
         */
        fun getAtributos(casa: String): MutableList<AtributoMagico> {
            val listaAtributosCasa: Map<String, Int>

            if (casa == CASA_SLYTHERIN) listaAtributosCasa = habilidadesSlytherin
            else if (casa == CASA_GRIFFINDORF) listaAtributosCasa = habilidadesGriffindorf
            else if (casa == CASA_RAVENCLAW) listaAtributosCasa = habilidadesRavenclaw
            else listaAtributosCasa = habilidadesHufflepuff

            // Inicializamos una lista vacía para añadir los atributos
            val listaAtributoMagico: MutableList<AtributoMagico> = mutableListOf()
            for ((clave, valor) in listaAtributosCasa) {
                listaAtributoMagico.add(AtributoMagico(clave, valor))
            }

            return listaAtributoMagico
        }

        /**
         * Función para asignar los atributos a una lista de Atributos Mágicos
         */
        fun setAtributos(
            habilidad: Int, inteligencia: Int, creatividad: Int, etica: Int,
            coraje: Int, lealtad: Int
        ): MutableList<AtributoMagico> {

            val listaAtributosMagico: MutableList<AtributoMagico> = mutableListOf()

            listaAtributosMagico.add(AtributoMagico(AtributoMagico.HABILIDAD, habilidad))
            listaAtributosMagico.add(AtributoMagico(AtributoMagico.INTELIGENCIA, inteligencia))
            listaAtributosMagico.add(AtributoMagico(AtributoMagico.CREATIVIDAD, creatividad))
            listaAtributosMagico.add(AtributoMagico(AtributoMagico.ETICA, etica))
            listaAtributosMagico.add(AtributoMagico(AtributoMagico.CORAJE, coraje))
            listaAtributosMagico.add(AtributoMagico(AtributoMagico.LEALTAD, lealtad))

            return listaAtributosMagico
        }
    }
}