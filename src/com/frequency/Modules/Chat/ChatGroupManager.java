package com.frequency.Modules.Chat;

/**
 * Title :
 * Author : 염형준
 * Date : 2017-03-01
 */


public class ChatGroupManager {
    private static ChatGroupManager ourInstance = new ChatGroupManager();

    private ChatGroupManager() {
    }

    public static ChatGroupManager getInstance() {
        return ourInstance;
    }
}
