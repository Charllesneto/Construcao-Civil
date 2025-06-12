
/**
 *
 * @author ThinkPad T470
 */
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class app {

    public class CursoJava {

        public static void main(String[] args) {
            exemploDate();
            exemploCalendar();
            exemploDateFormat();
            exemploSimpleDateFormat();
            exemploLocalDate();
            exemploLocalTime();
            exemploLocalDateTime();
        }

        public static void exemploDate() {
            Date dataAtual = new Date();
            System.out.println(dataAtual);
        }

        public static void exemploCalendar() {
            Calendar calendario = Calendar.getInstance();
            System.out.println(calendario.getTime());
        }

        public static void exemploDateFormat() {
            DateFormat formato = DateFormat.getDateTimeInstance();
            Date dataAtual = new Date();
            System.out.println(formato.format(dataAtual));
        }

        public static void exemploSimpleDateFormat() {
            Date dataAtual = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            System.out.println(formato.format(dataAtual));
        }

        public static void exemploLocalDate() {
            LocalDate data = LocalDate.now();
            System.out.println(data);
        }

        public static void exemploLocalTime() {
            LocalTime hora = LocalTime.now();
            System.out.println(hora);
        }

        public static void exemploLocalDateTime() {
            LocalDateTime dataHora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            System.out.println(dataHora.format(formato));
        }
    }
    
}
