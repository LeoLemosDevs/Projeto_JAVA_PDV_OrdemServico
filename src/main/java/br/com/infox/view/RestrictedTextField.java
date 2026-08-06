package br.com.infox.view;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Utilitário para limitar a quantidade de caracteres em JTextFields.
 * Substitui a antiga biblioteca Atxy2k.
 */
public class RestrictedTextField {
    
    private final JTextField textField;
    
    public RestrictedTextField(JTextField textField) {
        this.textField = textField;
    }
    
    public void setLimit(final int limit) {
        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str == null) {
                    return;
                }
                if ((getLength() + str.length()) <= limit) {
                    super.insertString(offs, str, a);
                }
            }
        });
    }
}
