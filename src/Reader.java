import jdk.nashorn.internal.ir.Terminal;

import javax.smartcardio.*;
import java.util.List;

/**
 * Created by lasse on 19/06/17.
 */
public class Reader {
    private static final TerminalFactory factory = TerminalFactory.getDefault();
    private List<CardTerminal> terminals;
    private CardTerminal terminal;



    public Reader() {
        try{
            terminals = factory.terminals().list();
            terminal = terminals.get(0);
        } catch(CardException e) {
            System.out.println(e.getMessage());

        }
    }
    public Reader(int position) {
        try{
            terminals = factory.terminals().list();
            terminal = terminals.get(position);
        } catch(CardException e) {
            System.out.println(e.getMessage());

        }
    }

    byte[] getReponseData(byte[] req,String protocol) {
        byte[] reponse =  {};
        try {
            Card card = terminal.connect(protocol);
            CardChannel channel = card.getBasicChannel();
            ResponseAPDU rep = channel.transmit(new CommandAPDU(req));
            return rep.getData();
        } catch(CardException ce) {
            System.out.println(ce.getMessage());
        }

       return reponse;
    }


}
