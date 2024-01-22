package com.gdy.board_project.Enum;

public enum BoardCategory {
    HELLO,FREE,GOLD;

    public static BoardCategory of(String category){
        if(category.equalsIgnoreCase("hello")){
            return BoardCategory.HELLO;
        }
        else if(category.equalsIgnoreCase("FREE")){
            return BoardCategory.FREE;
        }
        else if(category.equalsIgnoreCase("GOLD")){
            return BoardCategory.GOLD;
        }
        else{
            return null;
        }
    }
}

