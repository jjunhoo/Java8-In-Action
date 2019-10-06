package Lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExcuteAroundPattern {
    public static void main(String ...args) throws IOException {
        // processFileLimited 사용
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        // processFile 사용 (한줄 읽기)
        // 람다식을 이용하여 인터페이스에서 선언한 메소드를 직접 작성하여 전달
        String oneLine = processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);

        // processFile 사용 (두줄 읽기)
        // 람다식을 이용하여 인터페이스에서 선언한 메소드를 직접 작성하여 전달
        String twoLine = processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLine);
    }
    public static String processFileLimited() throws IOException {
        // try - with - resource
        try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/Jun/IdeaProjects/Java8_In_Action/src/main/java/Lambda/data.txt"))) {
            return br.readLine();
        }
    }
    // 파라미터에 따라 동작이 달라지도록 하기 위한 메소드 생성
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/Jun/IdeaProjects/Java8_In_Action/src/main/java/Lambda/data.txt"))) {
            return p.process(br);
        }
    }
    // 동작 파라미터를 위한 인터페이스 생성
    public interface BufferedReaderProcessor {
        public String process(BufferedReader b) throws IOException;
    }
}
