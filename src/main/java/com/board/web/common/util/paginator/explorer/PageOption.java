package com.board.web.common.util.paginator.explorer;

public class PageOption {


    private String formId = "searchForm";

    private String link = "pageNo";

    private String pageFormat = "@";

    private String start = "처음";
    private String prev = "이전";

    private String next = "다음";
    private String end = "끝";

    public String formId() {
        return formId;
    }

    public String link() {
        return link;
    }

    public String pageFormat() {
        return pageFormat;
    }

    public String prev() {
        return prev;
    }

    public String next() {
        return next;
    }
    
    public String start() {
        return start;
    }

    public String end() {
        return end;
    }
    

}
