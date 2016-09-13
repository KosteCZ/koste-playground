package cz.koscak.jan.mytasks2;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jkoscak on 12. 9. 2016.
 */
public class UserAccount {

    private static UserAccount userAccount = null;
    private String username = null;
    private static Context context = null;

    private UserAccount() {
    }

    public static UserAccount newInstance() {

        if (userAccount == null) {
            userAccount = new UserAccount();
            userAccount.setUsername(context);
        }
        return userAccount;
    }

    public void setUsername(Context context) {
        if (context != null) {
            AccountManager manager = AccountManager.get(context);
            Account[] accounts = manager.getAccountsByType("com.google");
            List<String> possibleEmails = new LinkedList<String>();

            for (Account account : accounts) {
                // TODO: Check possibleEmail against an email regex or treat
                // account.name as an email address only for certain account.type values.
                possibleEmails.add(account.name);
            }

            if (!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
                String email = possibleEmails.get(0);

                username = email;
            /*
            String[] parts = email.split("@");

            if (parts.length > 1) {
                username = parts[0];
            }
            */
            }
        }

    }

    public String getUsername() {
        return username;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        UserAccount.context = context;
    }

}
