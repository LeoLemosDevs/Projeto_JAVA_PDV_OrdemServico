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
    private int limit = -1;
    private boolean onlyNums = false;
    
    public RestrictedTextField(JTextField textField) {
        this.textField = textField;
        this.textField.setDocument(new RestrictedDocument());
    }
    
    public void setLimit(int limit) {
        this.limit = limit;
    }
    
    public void setOnlyNums(boolean onlyNums) {
        this.onlyNums = onlyNums;
    }
    
    private class RestrictedDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }
            if (onlyNums && !str.matches("\\d+")) {
                return;
            }
            if (limit > 0 && (getLength() + str.length()) > limit) {
                return;
            }
            super.insertString(offs, str, a);
        }
    }
}
