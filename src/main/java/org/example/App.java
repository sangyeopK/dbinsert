package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.Container.sc;

public class App {
    public void run() {
        List<Article> articleList = new ArrayList<>();
        int articleId = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;



        try{
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";

            conn = DriverManager.getConnection(url, "root", "");

            String sql = "INSERT INTO article";
            sql += " SET regDate = now()";
            sql += ", updateDate = now()";
            sql += ", title = 'test2'";
            sql += ", content = 'asdasd'";

            pstmt = conn.prepareStatement(sql);
            int affectRows = pstmt.executeUpdate();

            System.out.println("affectRows = " + affectRows);

            System.out.println("연결 성공");
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }


        while (true) {
            System.out.println("명령어)");
            String cmd = sc.nextLine();
            if (cmd.equals("등록")) {
                articleId++;
                System.out.println("게시물 등록");
                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("제목 : ");
                String content = sc.nextLine();
                System.out.println(articleId);
                Article article = new Article(articleId, title, content);
                articleList.add(article);
            } else if (cmd.equals("목록")) {
                System.out.println("== 게시물 리스트==");
                if(articleList.isEmpty()){
                    System.out.println("게시물이 존재하지 않습니다");
                }
                for (int i = articleList.size() - 1; i >= 0; i--) {
                    Article article = articleList.get(i);
                    System.out.println("번호 : " + article.getId() + " / 제목 : " + article.getTitle() + " / 내용 : " + article.getContent());
                }
                System.out.println("글 수 : " + articleList.size());
            } else if (cmd.equals("종료")){
                System.out.println("시스템 종료");
            } else {
                System.out.println("명령어를 입력해주세요");
            }
        }
    }
}
