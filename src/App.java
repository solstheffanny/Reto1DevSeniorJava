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

        public static void seleccionDestino() {
            int seleccion;
            do {
                System.out.println("\n************************************************");
                System.out.println(" Selecciona un planeta:");
                System.out.println("************************************************\n");
                for (int i = 0; i < planetas.length; i++) {
                    System.out.println(
                            "Planeta " + (i + 1) + ". " + planetas[i] + ", está a " + formatNumber(distancia[i]) + "km.\n");
                }
                System.out.print("\nIntroduce el número del planeta seleccionado: ");
                seleccion = scanner.nextInt();
    
                if (seleccion < 1 || seleccion > planetas.length) {
                    System.out.println("Selección inválida. Por favor, elija de nuevo.");
                } else {
                    planetaSeleccionado = seleccion - 1;
                    System.out.println("\nHas seleccionado el planeta: " + planetas[planetaSeleccionado] + ", que está a "
                            + formatNumber(distancia[planetaSeleccionado]) + " km.\n"
                            + descripcionPlaneta[planetaSeleccionado] + "\n\n");
                }
            } while (seleccion < 1 || seleccion > planetas.length);
            presionaEnter();
        }
    
        public static void seleccionDeNave() {
            int seleccionj;
    
            do {
                System.out.println("\n************************************************");
                System.out.println("Selecciona una nave:");
                System.out.println("************************************************\n\n");
    
                for (int j = 0; j < naves.length; j++) {
                    System.out.println(
                            "Nave " + (j + 1) + ". " + naves[j] + ", con una velocidad de " + formatNumber(velocidadNave[j])
                                    + " km/h, y una capacidad máxima de " + formatNumber(pasajeros[j]) + " pasajeros.\n");
                }
                System.out.print("\nIntroduce el número de la nave: ");
                seleccionj = scanner.nextInt();
    
                if (seleccionj < 1 || seleccionj > naves.length) {
                    System.out.println("Selección inválida. Inténtalo de nuevo.");
                } else {
                    naveSeleccionada = seleccionj - 1; // Ajustar a índice base 0
                    System.out.println("\nHas seleccionado la nave: " + naves[naveSeleccionada] + ", que viaja a "
                            + formatNumber(velocidadNave[naveSeleccionada]) + " km/h y con una capacidad máxima de "
                            + formatNumber(pasajeros[naveSeleccionada]) + " pasajeros.\n");
    
                    // llamamos el metodo seleccion de pasajeros
                    seleccionDePasajeros();
                }
            } while (seleccionj < 1 || seleccionj > naves.length);
            calculoDeTiempo();
            presionaEnter();
        }
    
        public static void seleccionDePasajeros() {
            System.out.println("Ingresa la cantidad de pasajeros que viajarán: ");
            cantidadDePasajeros = scanner.nextInt();
    
            if (cantidadDePasajeros <= pasajeros[naveSeleccionada]) {
                System.out.println("\nLa nave espacial " + naves[naveSeleccionada] + " es adecuada para "
                        + formatNumber(cantidadDePasajeros) + " pasajeros \n ");
            } else {
                System.out.println(
                        "\nLa nave seleccionada no es adecuada para " + formatNumber(cantidadDePasajeros) + " pasajeros. " +
                                "\nElige otra nave con mayor capacidad o reduce la cantidad de pasajeros.");
                seleccionDeNave();
            }
    
        }
    
        public static void calculoDeTiempo() {
            System.out.println("\nDistancia total del recorrido al planeta " + planetas[planetaSeleccionado] + " es de: "
                    + formatNumber(distancia[planetaSeleccionado]) + " km.");
            totalHoras = distancia[planetaSeleccionado] / velocidadNave[naveSeleccionada];
            totalDias = totalHoras / 24;
            totalMeses = totalDias / 30;
            totalAños = totalDias / 365;
            // Tiempo = distancia / velocidad
            // dias = horas / 24
            // meses = dias / 30
            // años = dias / 365
        }
    