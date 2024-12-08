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
        public static void main(String[] args) {
            menuPrincipal();
            scanner.close();
        }
    
        public static void menuPrincipal() {
            int opcion;
    
            do {
                System.out.println("\n************************************************");
                System.out.println("**************** Menú Principal ****************");
                System.out.println("************************************************\n\n");
    
                // Mostrar las opciones con subrayado si ya han sido seleccionadas
                for (int i = 0; i < 5; i++) {
                    String estado = opcionesSeleccionadas[i] ? "\033[4m" : ""; // Subrayado si seleccionada
                    String reset = opcionesSeleccionadas[i] ? "\033[0m" : ""; // Restablecer formato
                    System.out.println((i + 1) + ". " + estado + getMenuDescripcion(i + 1) + reset);
                }
    
                System.out.println("6. Reiniciar las opciones");
    
                System.out.print("\nElige una opción: ");
                opcion = scanner.nextInt();
    
                    switch (opcion) {
                        case 1:
                        if (opcionesSeleccionadas[0]){
                                System.out.println("Esta opción ya ha sido seleccionada. Elige otra o reinicia las opciones.");
                            } else {
                        seleccionDestino();
                        opcionesSeleccionadas[0] = true; // Opción 1 completada
                            }
                        break;
    
                        case 2:
                        if (opcionesSeleccionadas[1]){
                            System.out.println("Esta opción ya ha sido seleccionada. Elige otra o reinicia las opciones.");
                        } else if (opcionesSeleccionadas[0]) { // Solo si la opción 1 fue completada
                            seleccionDeNave();
                            opcionesSeleccionadas[1] = true; // Opción 2 completada
                        } else {
                            System.out.println("Debes completar la opción 1 antes de seleccionar la 2.");
                            presionaEnter();
                        }
                        break;
    
                        case 3:
                        if (opcionesSeleccionadas[2]){
                            System.out.println("Esta opción ya ha sido seleccionada. Elige otra o reinicia las opciones.");
                        } else if (opcionesSeleccionadas[1]) { // Solo si la opción 2 fue completada
                            calculoDeRecursos();
                            opcionesSeleccionadas[2] = true; // Opción 3 completada
                        } else {
                            System.out.println("Debes completar la opción 2 y 1 antes de seleccionar la 3.");
                            presionaEnter();
                        }
                        break;
                        
                        case 4:
                        if (opcionesSeleccionadas[3]){
                            System.out.println("Esta opción ya ha sido seleccionada. Elige otra o reinicia las opciones.");
                        } else if (opcionesSeleccionadas[2]) { // Solo si la opción 3 fue completada
                            inicioViaje();
                            opcionesSeleccionadas[3] = true; // Opción 4 completada
                        } else {
                            System.out.println("Debes completar la opción 1,2 y 3 antes de seleccionar la 4.");
                            presionaEnter();
                        }
                        break;
                        
                        case 5:
                            System.out.println("Gracias por tu viaje. Finalización del recorrido.");
                            presionaEnter();
                        break;
                        
                        case 6:
                        reinicio();
                        presionaEnter();
                        break;
    
                        default: 
                        System.err.println("Opción no válida.");
                    }
                
            } while (opcion != 5);
    
        }
    
        public static String getMenuDescripcion(int opcion) {
    
            return switch (opcion) {
                case 1 -> "Elige a qué planeta quieres ir";
                case 2 -> "Elige la nave para el viaje interplanetario";
                case 3 -> "Calcula tus recursos para el viaje";
                case 4 -> "Comienza el viaje interplanetario";
                case 5 -> "Finaliza el recorrido";
                default -> "";
            };
        }

    