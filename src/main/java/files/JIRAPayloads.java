package files;

public class JIRAPayloads {
    public static String createIssuePayload(String summary)
    {
        return "{\n" +
                "  \"fields\": {\n" +
                "    \"summary\": \""+summary+"\",\n" +
                "    \"description\": \"Test\",\n" +
                "    \"project\": {\n" +
                "      \"key\": \"RES\"\n" +
                "    },\n" +
                "    \"issuetype\": {\n" +
                "      \"name\": \"Ошибка\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    public static String createIssueCommentPayload()
    {
        return "{\n" +
                "  \"visibility\": {\n" +
                "    \"type\": \"role\",\n" +
                "    \"value\": \"Administrators\"\n" +
                "  },\n" +
                "  \"body\": \"Test Comment\"\n" +
                "}";
    }

}
