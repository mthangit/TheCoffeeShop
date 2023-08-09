package view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// ...

class NumericDocument extends PlainDocument {
    private int maxLength; // Độ dài tối đa của chuỗi
    private boolean allowNegative; // Cho phép số âm hay không

    public NumericDocument(int maxLength, boolean allowNegative) {
        this.maxLength = maxLength;
        this.allowNegative = allowNegative;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        String currentText = getText(0, getLength());
        String newText = currentText.substring(0, offset) + str + currentText.substring(offset);

        // Kiểm tra độ dài chuỗi và số nguyên
        if (isInteger(newText) && newText.length() <= maxLength) {
            super.insertString(offset, str, attr);
        }
    }

    @Override
    public void replace(int offset, int length, String str, AttributeSet attrs) throws BadLocationException {
        if (str == null) {
            return;
        }

        String currentText = getText(0, getLength());
        String newText = currentText.substring(0, offset) + str + currentText.substring(offset + length);

        // Kiểm tra độ dài chuỗi và số nguyên
        if (isInteger(newText) && newText.length() <= maxLength) {
            super.replace(offset, length, str, attrs);
        }
    }

    private boolean isInteger(String text) {
        // Kiểm tra xem chuỗi có chứa số nguyên hay không
        if (allowNegative) {
            return text.matches("-?\\d+");
        } else {
            return text.matches("\\d+");
        }
    }
}
