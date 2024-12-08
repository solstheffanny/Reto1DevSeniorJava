public class app {

        static Scanner scanner = new Scanner(System.in);
    
        static String[] planetas = { "Mercurio", "Venus", "Marte", "Jupiter", "Saturno" };
        static double[] distancia = { 91_700_000, 42_400_000, 78_300_000, 628_900_000, 1_284_400_000 }; // distancia en km
        static String[] descripcionPlaneta = { "El más cercano al Sol, pequeño y rocoso.",
                "Similar a la Tierra en tamaño, pero muy caliente y con una atmósfera densa.",
                "Conocido como el Planeta Rojo, frío y desértico.",
                "El más grande, con una gran mancha roja y muchos satélites.",
                "Famoso por sus anillos, compuesto principalmente de hidrógeno y helio." };
    
        static String[] naves = { "Destructor Estelar - Nave de Turismo", "Planet Express - Nave de Trabajo",
                "OVNI - Nave de Combate",
                "Halcon Milenario - Nave de Carreras", "TIE Interceptor - Nave Privada" };
        static double[] velocidadNave = { 1_500, 2_500, 25_000, 50_000, 70_000 }; // velocidad en Km/h
        static int[] pasajeros = { 5_000_000, 50_000, 5_000, 50, 5 }; // Cantidad de pasajeros de la nave
    
        static boolean[] opcionesSeleccionadas = new boolean[5]; // Solo para las opciones 1-5 del menú principal
    
        static int planetaSeleccionado = -1;
        static int naveSeleccionada = -1;
        static int cantidadDePasajeros = -1;
        static double totalAños;
        static double totalMeses;
        static double totalDias;
        static double totalHoras;
        static double factorCombustible = 0.7;
        static double factorOxigeno = 0.8;
        static double porcentajeAdicional = 50; // Si en algún momento se quiere asignar un porcentaje mayor o menor para
                                                // que la persona administre los recursos se puede hacer
        static double combustibleExtra = 0;
        static double oxigenoExtra = 0;
        static double combustibleMinimo = 0;
        static double oxigenoMinimo = 0;
        static double totalCombustible;
        static double totalOxigeno;
        static int porcentajeOxigeno;
    
        static final double inicialCombustible = totalCombustible; // Se toma el valor inicial para calcular el porcentaje
                                                                   // de perdidas con base a este valor sin modificaciones
        static final double inicialOxigeno = totalOxigeno;
    
        public static void main(String[] args) {
            menuPrincipal();
            scanner.close();
        }
    