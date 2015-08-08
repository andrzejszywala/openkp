/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.openkp.business.walidacja;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Walidator dla numeru pesel
 * 
 * @author Andrzej Szywała
 */
public class IbanValidator implements ConstraintValidator<Iban, String> {

    @Override
    public boolean isValid(String iban, ConstraintValidatorContext context) {
        if (iban == null) {
            return true;
        }
        // Algorytm (c) R.J.ĹťyĹĹa 2000-2004 */
        iban = iban.toUpperCase().replaceAll("[\\p{Punct}\\p{Space}]*", "");
        if (!iban.matches("^[A-Z]{2}[0-9]{12,}"))
            return false;
        // if (iban.length() < 16)
        // return false;
        // 1. Pierwsze 4 znaki na koniec
        iban = iban.substring(4, iban.length()) + iban.substring(0, 4);
        // 2. Litery na cyfry
        for (int i = 0; i < iban.length(); i++) {
            char c = iban.charAt(i);
            if (Character.isUpperCase(c)) {
                int code = Character.getNumericValue(c);
                iban = iban.substring(0, i) + code + iban.substring(i + 1, iban.length());
            }
        }
        // 3. Modulo 97
        int mod = 0;
        int isize = iban.length();
        for (int i = 0; i < isize; i = i + 6) {
            try {
                mod = Integer.parseInt("" + mod + iban.substring(i, i + 6), 10) % 97;
            } catch (StringIndexOutOfBoundsException e) {
                return false;
            }
        }
        return (mod == 1);
    }

    @Override
    public void initialize(Iban constraintAnnotation) {

    }
}
