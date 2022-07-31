import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yejin.article.ArticleService;
import com.yejin.article.dto.ArticleDto;
import com.yejin.util.Ut;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    void test_assertThat() {
        int rs = 10 + 20;
        assertThat(rs).isEqualTo(30);
    }


    @Test
    void lab_assertThat() throws JsonProcessingException {

        LocalDate date= LocalDate.now();
        ArticleDto articleDto = new ArticleDto(1,"제목", "내용", "예진",date,date);

        String jsonStr=Ut.json.toStr(articleDto,"");
        System.out.println(jsonStr);
        System.out.println("""
                {"id":1,"title":"제목","body":"내용","writer":"예진","createDate":"2022-07-28","modifyDate":"2022-07-28"}
                """.trim()
        );
        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                {"id":1,"title":"제목","body":"내용","writer":"예진","createDate":"2022-07-28","modifyDate":"2022-07-28"}
                """.trim());
        assertThat(articleDto.getId()).isEqualTo(1L);
        assertThat(articleDto.getTitle()).isEqualTo("제목");
        assertThat(articleDto.getBody()).isEqualTo("내용");
        assertThat(articleDto.getWriter()).isEqualTo("예진");
        assertThat(articleDto.getCreateDate()).isInstanceOf(LocalDate.class);
        assertThat(articleDto.getModifyDate()).isInstanceOf(LocalDate.class);
//        assertThat(articleMap.get("modifiedDate")).isInstanceOf(LocalDateTime.class);
//        assertThat(articleMap.get("isBlind")).isEqualTo(false);

    }
    // 간단하게 시간 체크하고 싶을때 not null
/*
    assertThat(articleDto.getCreatedDate()).isNotNull();
    assertThat(articleDto.getModifiedDate()).isNotNull();

    // instanceof
    assertThat(articleMap.get("createdDate")).isInstanceOf(LocalDateTime.class);
    assertThat(articleMap.get("modifiedDate")).isInstanceOf(LocalDateTime.class);
*/
    // 정밀하게 시간 체크하고 싶을때,
/*    @Test
    public void selectDatetime() {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT NOW()");

        LocalDateTime datetime = sql.selectDatetime();

        long diff = ChronoUnit.SECONDS.between(datetime, LocalDateTime.now());

        assertThat(diff).isLessThanOrEqualTo(1L);
    }*/

    @Test
    void ObjectMapper__articleDtoToJsonStr() {

        LocalDate date= LocalDate.now();

        ArticleDto articleDto = new ArticleDto(1,"제목", "내용", "예진",date,date);
        String jsonStr = Ut.json.toStr(articleDto, "");
        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                {"id":1,"title":"제목","body":"내용","writer":"예진","createDate":"2022-07-28","modifyDate":"2022-07-28"}
                """.trim());
    }

    @Test
    void ObjectMapper__articleDtoListToJsonStr() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        LocalDate date= LocalDate.now();

        articleDtos.add(new ArticleDto(1, "제목", "내용","예진",date,date));
        articleDtos.add(new ArticleDto(2, "제목2", "내용2","예진2",date,date));

        String jsonStr = Ut.json.toStr(articleDtos, "");
        assertThat(jsonStr).isEqualTo("""
                [{"id":1,"title":"제목","body":"내용","writer":"예진","createDate":"2022-07-28","modifyDate":"2022-07-28"},{"id":2,"title":"제목2","body":"내용2","writer":"예진2","createDate":"2022-07-28","modifyDate":"2022-07-28"}]
                                    """.trim());
    }



    @Test
    void ObjectMapper__jsonStrToObj() {
        LocalDate date= LocalDate.now();

        ArticleDto articleDtoOrigin = new ArticleDto(1, "제목", "내용","예진",date,date);
        String jsonStr = Ut.json.toStr(articleDtoOrigin, "");
        System.out.println(Ut.json.toObj(jsonStr, ArticleDto.class, null));
        Ut.json.toObj(jsonStr, ArticleDto.class, null);
        ArticleDto articleDtoFromJson = Ut.json.toObj(jsonStr, ArticleDto.class, null);

        assertThat(articleDtoOrigin).isEqualTo(articleDtoFromJson);
    }
    @Test
    void ObjectMapper__articleDtoMapToJsonStr() {

        LocalDate date= LocalDate.now();
        Map<String, ArticleDto> articleDtoMap;
        articleDtoMap = new HashMap<>();
        articleDtoMap.put("가장오래된", new ArticleDto(1, "제목", "내용","예진",date,date));
        articleDtoMap.put("최신", new ArticleDto(2, "제목2", "내용2","예진",date,date));
        String jsonStr = Ut.json.toStr(articleDtoMap, "");
        assertThat(jsonStr).isEqualTo("""
                {"가장오래된":{"id":1,"title":"제목","body":"내용","writer":"예진","createDate":"2022-07-28","modifyDate":"2022-07-28"},"최신":{"id":2,"title":"제목2","body":"내용2","writer":"예진","createDate":"2022-07-28","modifyDate":"2022-07-28"}}
                """.trim());
    }


    @Test
    void ObjectMapper__jsonStrToArticleDtoList() {
        List<ArticleDto> articleDtos = new ArrayList<>();

        LocalDate date= LocalDate.now();

        articleDtos.add(new ArticleDto(1, "제목", "내용","예진",date,date));
        articleDtos.add(new ArticleDto(2, "제목2", "내용2","예진",date,date));

        String jsonStr = Ut.json.toStr(articleDtos, "");
        Ut.json.toObj(jsonStr, new TypeReference<>() {
        }, null);
//        System.out.println(Ut.json.toObj(jsonStr, new TypeReference<>() {
//        }, null)); // T 타입은 List 인줄 모르기 때문에 println 이 안되는구나
        List<ArticleDto> articleDtosFromJson = Ut.json.toObj(jsonStr, new TypeReference<>() {
        }, null);

        assertThat(articleDtosFromJson).isEqualTo(articleDtos);
    }
        @Test
    void ObjectMapper__jsonStrToArticleDtoMap() {
            LocalDate date= LocalDate.now();
            Map<String, ArticleDto> articleDtoMap;
            articleDtoMap = new HashMap<>();
            articleDtoMap.put("가장오래된", new ArticleDto(1, "제목", "내용","예진",date,date));
            articleDtoMap.put("최신", new ArticleDto(2, "제목2", "내용2","예진",date,date));
            String jsonStr = Ut.json.toStr(articleDtoMap, "");

           // System.out.println(jsonStr);

            Map<String,ArticleDto> articleDtoMapFromJson = Ut.json.toObj(jsonStr, new TypeReference<>(){}, null);
            assertThat(articleDtoMapFromJson).isEqualTo(articleDtoMap);
    }




}