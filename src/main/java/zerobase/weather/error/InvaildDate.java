package zerobase.weather.error;

public class InvaildDate extends RuntimeException{
    private static final String message = "너무 과거 혹은 미래의 날짜입니다";
    public InvaildDate() {
        super(message);
    }
}
