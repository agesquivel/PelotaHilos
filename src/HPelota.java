import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class HPelota extends JFrame{
    private Button inicio;
    private Balon balon;

    public HPelota(){
        super("Ejemplo de Hilos");
        this.setSize(400,400);
        this.setBackground(Color.yellow);
        this.addComponentes();
        this.addEventos();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addComponentes(){
        setLayout(new FlowLayout());
        inicio = new Button("Iniciar");
        add(inicio);
    }

    public void addEventos(){
        addWindowListener (new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        inicio.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Graphics g = getGraphics();
                Balon b = new Balon(g);
                //b.pintar();
                b.start();
            }
        });
    }

    public static void main(String[] args){
        HPelota Juego = new HPelota();

    }

    class Balon extends Thread{
        private int x;
        private int y;
        private int diametro;
        private int xInc;
        private int yInc;
        private Graphics g;

        public Balon(Graphics g) {
            x=(int)(Math.random()*300+1);
            y=(int)(Math.random()*300+1);
            diametro=25;
            double signo1=Math.random();
            double signo2=Math.random();
            xInc = 5*(signo1>0.5?1:-1);   //if (signo1>0.5) return 1 else return -1
            yInc = signo2>0.5?5:-5;
            this.g = g;
        }

        public void run(){
            Color colorPerlota = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
            for (int n=1; true; n++){
                g.setColor(Color.YELLOW);
                g.fillOval(x,y,diametro,diametro);

                if (x + xInc <= 0)
                    xInc = -xInc;

                if (x + xInc >= 400)
                    xInc = -xInc;

                if (y + yInc <= 0)
                    yInc = -yInc;

                if (y + yInc >= 400)
                    yInc = -yInc;

                x+=xInc;
                y+=yInc;

                g.setColor(colorPerlota);
                g.fillOval(x,y,diametro,diametro);

                try{
                    Thread.sleep(10);
                }
                catch(InterruptedException e){
                }
            } //for

            //g.setColor(Color.YELLOW);
            //g.fillOval(x,y,diametro,diametro);
        }
    } //Bal�n
} //HPelota