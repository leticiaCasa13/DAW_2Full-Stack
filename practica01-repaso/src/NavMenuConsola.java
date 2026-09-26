import java.util.Scanner;


public class NavMenuConsola {

        public static void main(String[] args)  {
            Scanner sc = new Scanner(System.in);



                int opcioInicio;

                do {

                    System.out.println("Menu Principal");
                    System.out.println("1.Gestion de usuaris");
                    System.out.println("2.Veure estat del sistema");
                    System.out.println("0. Sortir");
                    System.out.print("Escull una opció: ");


                    opcioInicio = sc.nextInt();


                    switch (opcioInicio) {


                        case 1:
                            System.out.println("Has escogill la opcio 1.");
                            obrirSubmenuUsuaris(sc);
                            break;
                        case 2:
                            System.out.println("El sistema està funcionant correctament.");
                            break;
                        case 0:
                            System.out.println("Torna al menu principal. ");
                            break;
                        default:
                            System.out.println("Opcio no valida.");
                            break;


                    }

                } while (opcioInicio != 0);
                sc.close();


            }



        public static void obrirSubmenuUsuaris(Scanner sc) {

            int opcioInicio;

            do {
                System.out.println("SubMenu");
                System.out.println("1. Llistar usuaris");
                System.out.println("2. Afegir un nou usuaris");
                System.out.println("0. Tornar al menu principal");
                System.out.println(" Escull una opcio: ");


                opcioInicio = sc.nextInt();


                switch (opcioInicio) {

                    case 1:

                        sc.nextLine();

                        System.out.print("Llistat d'usuaris: ");

                        System.out.println("- Anna");
                        System.out.println("- Joan");
                        System.out.println("- Maria");
                        break;


                    case 2:

                        sc.nextLine();

                        System.out.print("Introdueix el nom del nou usuari: ");
                        String nom = sc.nextLine();
                        System.out.println("Usuari afegit: " + nom);
                        break;

                    case 0:
                        System.out.println("Tornant al menú principal...");
                        break;

                    default:
                        System.out.println("Opció no vàlida.");
                        break;
                }

            } while (opcioInicio != 0);

        }

}

















