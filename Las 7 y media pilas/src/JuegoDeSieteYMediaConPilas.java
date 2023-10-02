import java.util.*;

public class JuegoDeSieteYMediaConPilas {
    public static void main(String[] args) {
        Stack<Carta> mazo = crearMazo();
        Stack<Carta> mano = new Stack<>();
        double puntaje = 0.0;
        Scanner scanner = new Scanner(System.in);

        // Barajar el mazo
        Collections.shuffle(mazo);

        while (!mazo.isEmpty()) {
            Carta carta = mazo.pop();
            mano.push(carta);
            puntaje += carta.getValor();

            System.out.println("Tu mano: ");
            for (Carta c : mano) {
                System.out.println(c);
            }

            System.out.println("Puntaje total: " + puntaje);

            if (puntaje >= 7.5) {
                break;
            }

            System.out.print("¿Quieres otra carta? (S/N): ");
            String respuesta = scanner.nextLine();

            if (!respuesta.equalsIgnoreCase("S")) {
                break;
            }
        }

        if (puntaje == 7.5) {
            System.out.println("¡Has conseguido 7.5! ¡Ganaste!");
        } else if (puntaje > 7.5) {
            System.out.println("Te has pasado de 7.5. ¡Has perdido!");
        } else {
            System.out.println("Te has retirado con un puntaje de " + puntaje + ". ¡Buena partida!");
        }

        scanner.close();
    }

    public static Stack<Carta> crearMazo() {
        Stack<Carta> mazo = new Stack<>();

        for (Palo palo : Palo.values()) {
            for (Numero numero : Numero.values()) {
                mazo.push(new Carta(numero, palo));
            }
        }

        return mazo;
    }
}

enum Palo {
    OROS, COPAS, ESPADAS, BASTOS
}

enum Numero {
    UNO(1), DOS(2), TRES(3), CUATRO(4), CINCO(5), SEIS(6), SIETE(7), SOTA(0.5), CABALLO(0.5), REY(0.5);

    private double valor;

    private Numero(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}

class Carta {
    private Numero numero;
    private Palo palo;

    public Carta(Numero numero, Palo palo) {
        this.numero = numero;
        this.palo = palo;
    }

    public double getValor() {
        return numero.getValor();
    }

    @Override
    public String toString() {
        return numero + " de " + palo;
    }
}