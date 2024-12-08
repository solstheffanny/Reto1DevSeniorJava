import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;


public class App {

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
    
        static boolean[] opcionesSeleccionadas = new boolean[5]; // El menu de las 6 opciones
    
        static int planetaSeleccionado = -1; 
        static int naveSeleccionada = -1;
        static int cantidadDePasajeros = -1;
        static double totalAños;
        static double totalMeses;
        static double totalDias;
        static double totalHoras;
        static double factorCombustible = 0.7; // Factor de consumo y desgaste de combustible que varia la distancia y la nave
        static double factorOxigeno = 0.8;     // Factor de consumo y desgaste de oxigeno que varia la cantidad de persona, la distancia y la nave
        static double porcentajeAdicional = 50; // Si en algún momento se quiere asignar un porcentaje mayor o menor de recursos adicionales
                                                
        static double combustibleExtra = 0;
        static double oxigenoExtra = 0;
        static double combustibleMinimo = 0;
        static double oxigenoMinimo = 0;
        static double totalCombustible;
        static double totalOxigeno;
        static int porcentajeOxigeno;
    
        static final double inicialCombustible = totalCombustible; // Se toma el valor inicial para calcular el porcentaje de perdidas con base a este valor sin modificaciones
        static final double inicialOxigeno = totalOxigeno;
    
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
    
                // Este for modifica y subraya las opciones del menú que ya han sido seleccionadas
                for (int i = 0; i < 5; i++) {
                    String estado = opcionesSeleccionadas[i] ? "\033[4m" : ""; // Subrayado si seleccionada
                    String reset = opcionesSeleccionadas[i] ? "\033[0m" : ""; // Cuando se restablece el formato
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
                        opcionesSeleccionadas[0] = true; 
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
    
            return switch (opcion) { //Nombre de las impresiones del menú
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


        public static void calculoDeRecursos() {
            // Lógica para calcular recursos
            // Calculo de tiempo es distancia / velocidad
    
            // El factor de combustible para viajes espacial es de 0.05 por km
            // El factor de oxigeno por persona por dia es de 0.8
    
            combustibleMinimo = distancia[planetaSeleccionado] * factorCombustible;
            oxigenoMinimo = totalDias * factorOxigeno * cantidadDePasajeros;
    
            System.out.println("\n************************************************");
            System.out.println("Analisis del viaje");
            System.out.println("************************************************\n\n");
    
            System.out.printf("%-40s%s%n", "Nave seleccionada: ->", naves[naveSeleccionada]);
            System.out.printf("%-40s%s%n", "Destino seleccionado: ->", planetas[planetaSeleccionado]);
            System.out.printf("%-40s%s%n", "Distancia desde la tierra: ->",
                    formatNumber(distancia[planetaSeleccionado]) + " km.");
            System.out.printf("%-40s%s%n", "Total pasajeros a transportar: ->",
                    formatNumber(cantidadDePasajeros) + " pasajeros.");
            System.out.printf("%-40s%s/%s/%s%n", "Duracion del viaje en dias/meses/años: ->",
                    formatNumber(totalDias), formatNumber(totalMeses), formatNumber(totalAños));
            System.out.printf("%-40s%s%s%n", "Cantidad de oxigeno en la nave: ->", formatNumber(oxigenoMinimo),
                    " unidades de oxigeno.");
            System.out.printf("%-40s%s%s%n", "Cantidad de combustible para la nave: ->", formatNumber(combustibleMinimo),
                    " unidades de combustible.");
    
            presionaEnter();
    
            System.out.println("\nTienes " + porcentajeAdicional
                    + "% adicional de unidades disponibles para disponer en oxigeno y combustible");
            System.out.println("Recuerda gestionar estas unidades ya que puedes tener imprevistos en el viaje ");
    
            seleccionRecursos();
    
            presionaEnter();
    
        }
    
        public static void seleccionRecursos() {
            char opcion;
            do {
                System.out.println("\n************************************************");
                System.out.println("Elige cómo asignar los recursos:");
                System.out.println("************************************************\n");
                System.out.println("P - Asignar un porcentaje personalizado");
                System.out.println("O - Asignar todas las unidades adicionales al oxígeno");
                System.out.println("C - Asignar todas las unidades adicionales al combustible");
                System.out.println("N - Mantener los recursos distribuidos (25% / 25%)");
                System.out.println("Introduce tu opción (P/O/C/N): ");
                opcion = scanner.next().toUpperCase().charAt(0);
    
                combustibleMinimo = distancia[planetaSeleccionado] * factorCombustible;
                oxigenoMinimo = totalDias * factorOxigeno * cantidadDePasajeros;
                totalOxigeno = oxigenoMinimo + oxigenoExtra;
                totalCombustible = combustibleMinimo + combustibleExtra;
    
                switch (opcion) {
                    case 'P':
                        boolean confirmado = false;
                        while (!confirmado) {
                            System.out.println(
                                    "Del 50% de recursos adicionales cuanto desea asignar al oxigeno (entre 0 y 50%): ");
                            porcentajeOxigeno = scanner.nextInt();
    
                            if (porcentajeOxigeno < 0 || porcentajeOxigeno > 50.0) {
                                System.err.println("Porcentaje inválido. Debe estar entre 0 y 50.");
                                continue;
                            }
                            oxigenoExtra = (porcentajeOxigeno / 100.0) * (oxigenoMinimo);
                            combustibleExtra = ((porcentajeAdicional - porcentajeOxigeno) / 100.0) * combustibleMinimo;
    
                            System.out.println("Resumen");
                            System.out.printf("Oxígeno adicional: %d%% (%s unidades adicionales).\n",
                                    porcentajeOxigeno, formatNumber(oxigenoExtra));
                            System.out.println("Combustible adicional: " + (int) (porcentajeAdicional - porcentajeOxigeno)
                                    + "% (" + formatNumber(combustibleExtra) + " unidades adicionales).");
    
                            System.out.print("¿Confirmar distribución? Si o No (S/N): ");
                            char confirmacion = scanner.next().toUpperCase().charAt(0);
    
                            if (confirmacion == 'S') {
                                confirmado = true;
                            } else {
                                System.out.println("Reasignando recursos...");
                            }
                        }
    
                        break;
                    case 'O':
                        oxigenoExtra = (porcentajeAdicional / 100.0) * oxigenoMinimo;
                        combustibleExtra = 0.0;
                        porcentajeOxigeno = 50;
                        break;
    
                    case 'C':
                        combustibleExtra = (porcentajeAdicional / 100.0) * combustibleMinimo;
                        oxigenoExtra = 0.0;
                        porcentajeOxigeno = 0;
                        break;
    
                    case 'N':
                        oxigenoExtra = (porcentajeAdicional / 200) * oxigenoMinimo;
                        combustibleExtra = (porcentajeAdicional / 200) * combustibleMinimo;
                        porcentajeOxigeno = (int) porcentajeAdicional / 2;
                        break;
                    default:
                        System.err.print("Opción inválida. Por favor, selecciona de nuevo.");
    
                }
    
            } while (opcion != 'P' && opcion != 'O' && opcion != 'C' && opcion != 'N');
    
            System.out.println("\nResumen de recursos asignados:");
            System.out.println(
                    "Oxígeno adicional: " + formatNumber(oxigenoExtra) + " adicional" + " (" + porcentajeOxigeno + "%)");
            System.out.println("Combustible adicional: " + formatNumber(combustibleExtra) + " adicional" + " ("
                    + (int) (porcentajeAdicional - porcentajeOxigeno) + "%)");
            System.out.println("Oxígeno total: " + formatNumber(oxigenoMinimo + oxigenoExtra) + " unidades.");
            System.out.println("Combustible total: " + formatNumber(combustibleMinimo + combustibleExtra) + " unidades");
        }
    
        public static void inicioViaje() {
            // Lógica para comenzar el viaje
            random();
            presionaEnter();
        }
    
        public static void random() {
    
            System.out.println("Eventos inesperados durante el recorrido: ");
            // Crear el JFrame
            JFrame frame = new JFrame("Viaje interespacial a " + planetas[planetaSeleccionado]);
            frame.setSize(700, 100);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    
            // Crear los componentes
            JProgressBar barra = new JProgressBar(0, 100);
            barra.setStringPainted(true);
    
            JLabel etiquetaEvento = new JLabel("Iniciando viaje...");
            etiquetaEvento.setHorizontalAlignment(SwingConstants.CENTER);
    
            JLabel etiquetaRecursos = new JLabel(
                    "Combustible: " + formatNumber(totalCombustible) + " | Oxígeno: " + formatNumber(totalOxigeno));
            etiquetaRecursos.setHorizontalAlignment(SwingConstants.CENTER);
    
            // Agregar los componentes al JFrame
            frame.add(barra);
            frame.add(etiquetaEvento);
            frame.add(etiquetaRecursos);
    
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    
            double reduccionCombustible = 0.009 * combustibleMinimo;
            double reduccionOxigeno = 0.009 * oxigenoMinimo;
    
            // Simular el avance del viaje
            Random random = new Random();
            for (int i = 0; i <= 100; i++) {
                barra.setValue(i); // Actualizar el progreso de la barra
    
                totalCombustible -= (reduccionCombustible); // Esta operacion descuenta 1 unidad de
                                                            // recursos cada 1% de viaje teniendo en
                                                            // cuenta la cantidad de combustible
                                                            // minimo sin tener en cuenta el
                                                            // combustible extra, de este modo existe
                                                            // un mayor margen de perdidas y
                                                            // ganancias del viaje
                totalOxigeno -= (reduccionOxigeno);
    
                // Mostrar los recursos actualizados
                etiquetaRecursos.setText(
                        "Combustible: " + formatNumber(totalCombustible) + " | Oxígeno: " + formatNumber(totalOxigeno));
    
                // Esta opcion verifica que si el recurso es = 0 se agotaron los recursos y
                // perdiste, esto evita valores negativos para controlar limites minimos
                if (totalCombustible <= 0 || totalOxigeno <= 0) {
                    etiquetaEvento.setText("¡Has perdido! Recursos agotados.");
                    etiquetaRecursos.setText("Combustible: " + Math.max(totalCombustible, 0) +
                            " | Oxígeno: " + Math.max(totalOxigeno, 0));
                    System.out.println("¡Has perdido! La nave se quedó sin recursos");
                    break;
    
                }
                // Generar un evento aleatorio
                if (i > 10 && i < 98 && random.nextInt(10) == 0) { // Probabilidad de 1/10
                    Object[] resultado = eventoAleatorio();
                    String evento = (String) resultado[0];
    
                    // Imprimir el evento
                etiquetaEvento.setText("Evento: " + evento);
    
                // Esperar 1 segundo antes de aplicar los cambios
                try {
                    Thread.sleep(1000); // Pausa de 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
    
                    // Mostrar el evento y recursos actualizados
                    etiquetaEvento.setText("Evento: " + evento);
                    etiquetaRecursos.setText(
                            "Combustible: " + formatNumber(totalCombustible) + " | Oxígeno: " + formatNumber(totalOxigeno));
    
                    System.out.println("Avance: " + i + "% || Evento: " + evento);
    
    
                    // Pausa para mostrar el evento
                    try {
                        Thread.sleep(5000); // Mostrar evento por 3 segundos
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
    
                    // Verificar si los recursos se han agotado tras el evento
                    if (totalCombustible <= 0 || totalOxigeno <= 0) {
                        etiquetaEvento.setText("¡Has perdido! Recursos agotados.");
                        etiquetaRecursos.setText("Combustible: " + Math.max(totalCombustible, 0) +
                                " | Oxígeno: " + Math.max(totalOxigeno, 0));
    
                                // Pausa para que se vea el mensaje antes de cerrar la ventana
                                try {
                                    Thread.sleep(5000); // Mantener la ventana abierta por 5 segundos
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
    
                        break;
                    }
    
                    // Volver al mensaje de progreso del viaje
                    etiquetaEvento.setText("Continuando el viaje...");
                }
    
                try {
                    Thread.sleep(600); // Pausa para simular el avance
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    
            // Finalizar el viaje si no se han agotado los recursos
            if (totalCombustible > 0 && totalOxigeno > 0) {
                etiquetaEvento.setText("¡Felicitaciones, has llegado a tu destino!");
                System.out.println("¡Felicitaciones, has llegado a tu destino!");    
            }
            
    
            try {
                Thread.sleep(5000); // Esperar antes de cerrar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.dispose();
        }

        public static Object[] eventoAleatorio() { // Creacion de eventos aleatorios
            String[] eventos = {
                    "Fallo del sistema de propulsión: -10% de combustible",
                    "Impacto con micrometeoritos: -5% de combustible y -5% de oxigeno",
                    "Problemas con el suministro de energía: -15% de oxigeno",
                    "Fugas de aire: -10% de oxigeno",
                    "Parada técnica en una estación espacial: +5% de combustible y +5% de oxigeno",
                    "Encontraste nave de ALF: +5% de combustible y +5% de oxigeno",
                    "Ataque alienigena: -15% de combustible y -15% de oxigeno",
                    "Encuentas suministros de nave abandonada en el espacio: +15% de combustible y +15% de oxigeno",
                    "Descubriste un nuevo recurso mineral: +10% de combustible",
                    "Problemas con la gravedad artificial: -8% de oxigeno",
                    "Éxito en la implementación de energia solar: +20% de combustible",
                    "Encontraste un asteroide rico en recursos: +10% de combustible y +10% de oxigeno"
            };
    
            double[] cambiosCombustible = { -0.1, -0.05, 0, 0, +0.05, 0.05, -0.15, +0.15, +0.1, 0, +0.20, +0.1 };
            double[] cambiosOxigeno = { 0, -0.05, -0.15, -0.1, +0.05, +0.05, -0.15, +0.15, 0, -0.08, 0, +0.1 };
    
    
            Random random = new Random();
            int indiceEvento = random.nextInt(eventos.length); // Seleccionar evento aleatorio
    
            String evento = eventos[indiceEvento];
            double cambioCombustible = cambiosCombustible[indiceEvento] * totalCombustible; // Basado en el valor inicial
            double cambioOxigeno = cambiosOxigeno[indiceEvento] * totalOxigeno; // Basado en el valor inicial
    
            // Aplicar el cambio de recursos por el evento
            totalCombustible += cambioCombustible;
            totalOxigeno += cambioOxigeno;
    
            // Evitar valores negativos
            totalCombustible = Math.max(totalCombustible, 0);
            totalOxigeno = Math.max(totalOxigeno, 0);
    
            return new Object[] { evento, cambioCombustible, cambioOxigeno };
        }
    
        public static void reinicio() {
            // Reiniciar todas las opciones
            for (int i = 0; i < opcionesSeleccionadas.length; i++) {
                opcionesSeleccionadas[i] = false; // Desmarcar todas las opciones
            }
        }

        public static String formatNumber(double number) {
            NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US); // Esta es una opcion de separador de miles
            formatter.setGroupingUsed(true); // Activar agrupación por miles
            formatter.setMaximumFractionDigits(2); // Limitar a 2 decimales
            formatter.setMinimumFractionDigits(0); // No mostrar decimales innecesarios como ".00"
            return formatter.format(number);
        }
    
        public static void presionaEnter() {
            System.out.println("\nPresiona 'Enter' para continuar...");
            scanner.nextLine();
            scanner.nextLine();
        }
    
    }
    
    