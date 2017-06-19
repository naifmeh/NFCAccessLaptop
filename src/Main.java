import javax.smartcardio.*;
import java.util.List;

/**
 * Created by lasse on 19/06/17.
 */
public class Main implements CardSensorListener{
    private static final byte[] GET_UUID = {(byte)0xFF,(byte)0xCA,(byte)0x00,(byte)0x00,(byte)0x00};
    public int counter = 3;
    public static void main(String[] args) {
        try {
            Main main = new Main();
            while (true && main.counter>0) {
                CardTerminal terminal = TerminalFactory.getDefault().terminals().list().get(0);
                System.out.println("Scanning...");
                if(terminal.isCardPresent()) main.counter--;
            }
        }catch(CardException e) {}



    }


    @Override
    public void onCardDetected() {
        Reader reader = new Reader();
        System.out.println(reader.getReponseData(GET_UUID,"T=0"));
    }
}
