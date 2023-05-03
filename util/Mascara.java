package util;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class Mascara {

    /*
     * CARACTERES UTILIZADOS PARA CRIAÇÃO DAS MÁSCARAS
     * '#' - Qualquer número válido.
     * 'U' - Qualquer caractere (maiúsculo). 
     * 'L' - Qualquer caractere (minúsculo). 
     * 'A' - Qualquer caractere ou número.
     * '*' - Qualquer caractere, número ou símbolo.
     */
    public static DefaultFormatterFactory criarMascara(String m){
        try {
            MaskFormatter mask = new MaskFormatter(m);
            mask.setPlaceholderCharacter('_');
            mask.setValueContainsLiteralCharacters(false);
            return new DefaultFormatterFactory(mask);
        } catch (Exception e) {
            return null;
        }
    }
    
}
