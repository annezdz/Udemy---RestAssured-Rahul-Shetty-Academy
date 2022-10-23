package faker;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.AssertJUnit.assertTrue;

public class Teste {

    @Test
    public void whenBothifyCalled_checkPatternMatches() throws Exception {

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        System.out.println(email);
        Matcher emailMatcher = Pattern.compile("\\w{4}\\d{2}@gmail.com").matcher(email);

        assertTrue(emailMatcher.find());
    }

    @Test
    public void givenValidService_whenRegexifyCalled_checkPattern() throws Exception {

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String alphaNumericString = fakeValuesService.regexify("[a-z1-9]{10}");
        System.out.println(alphaNumericString);
        Matcher alphaNumericMatcher = Pattern.compile("[a-z1-9]{10}").matcher(alphaNumericString);

        assertTrue(alphaNumericMatcher.find());
    }
}
