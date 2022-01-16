package com.authapp.API;

import android.util.Log;

import com.authapp.POJO.AllUser;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.RequestBody;


public class APICall
{


    public AllUser GetLogin(String user_name, String password)
    {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();

        FormBody.Builder builder = new FormBody.Builder()
                .add("user_name",user_name)
                .add("password",password);

        RequestBody body = builder.build();
        String output = httpCall.POST(APIResource.LOGIN_URL,body);
        try {
            Log.d("Rittz",output);
        }
        catch (Exception e)
        {
            Log.e("Rittz",e.getMessage());
        }
        result = new Gson().fromJson(output,AllUser.class);
        return result;
    }

    public AllUser ForgotPassword(String user_name)
    {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();

        FormBody.Builder builder = new FormBody.Builder()
                .add("user_name",user_name);

        RequestBody body = builder.build();
        String output = httpCall.POST(APIResource.FORGOT_URL,body);
        try {
            Log.d("Rittz",output);
        }
        catch (Exception e)
        {
            Log.e("Rittz",e.getMessage());
        }
        result = new Gson().fromJson(output,AllUser.class);
        return result;
    }

    public AllUser SendOTPMail(String user_email , String msg)
    {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();

        FormBody.Builder builder = new FormBody.Builder()
                .add("user_email",user_email)
                .add("email_msg",msg);

        RequestBody body = builder.build();
        String output = httpCall.POST(APIResource.OPT_EMAIL_URL,body);
        try {
            Log.d("Rittz",output);
        }
        catch (Exception e)
        {
            Log.e("Rittz",e.getMessage());
        }
        result = new Gson().fromJson(output,AllUser.class);
        return result;
    }

    public AllUser PostRegister( String name, String email, String mobile, String pass)
    {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();

        FormBody.Builder builder = new FormBody.Builder()
                .add("name",name)
                .add("email",email)
                .add("mobile",mobile)
                .add("password",pass);

        RequestBody body = builder.build();
        String output = httpCall.POST(APIResource.REGISTER_URL,body);
        try {
            Log.d("Rittz",output);
        }
        catch (Exception e)
        {
            Log.e("Rittz",e.getMessage());
        }
        result = new Gson().fromJson(output,AllUser.class);
        return result;
    }

    public AllUser PostPasswordUpdate(String utype ,String user_id,String pass, String opass)
    {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();

        FormBody.Builder builder = new FormBody.Builder()
                .add("user_type",utype)
                .add("user_id",user_id)
                .add("opassword",opass)
                .add("password",pass);

        RequestBody body = builder.build();
        String output = httpCall.POST(APIResource.POST_PASSWORD_UPDATE,body);
        try {
            Log.d("Rittz",output);
        }
        catch (Exception e)
        {
            Log.e("Rittz",e.getMessage());
        }
        result = new Gson().fromJson(output,AllUser.class);
        return result;
    }

    public AllUser GetProfile(String user_id , String user_type)
    {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();

        FormBody.Builder builder = new FormBody.Builder()
                .add("user_id",user_id)
                .add("user_type",user_type);

        RequestBody body = builder.build();
        String output = httpCall.POST(APIResource.GET_PROFILE_URL,body);
        try {
            Log.d("Rittz",output);
        }
        catch (Exception e)
        {
            Log.e("Rittz",e.getMessage());
        }
        result = new Gson().fromJson(output,AllUser.class);
        return result;
    }

    public AllUser PostProfileUpdate(String user_id  ,String name, String email, String mobile)
    {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();

        FormBody.Builder builder = new FormBody.Builder()
                .add("user_id",user_id)
                .add("name",name)
                .add("email",email)
                .add("mobile",mobile);

        RequestBody body = builder.build();
        String output = httpCall.POST(APIResource.POST_PROFILE_UPDATE,body);
        try {
            Log.d("Rittz",output);
        }
        catch (Exception e)
        {
            Log.e("Rittz",e.getMessage());
        }

        result = new Gson().fromJson(output,AllUser.class);
        return result;
    }


    public AllUser GetDocumentList()
    {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();

        FormBody.Builder builder = new FormBody.Builder();

        RequestBody body = builder.build();
        String output = httpCall.POST(APIResource.DOCUMENT_LIST,body);
        try {
            Log.d("Rittz",output);
        }
        catch (Exception e)
        {
            Log.e("Rittz",e.getMessage());
        }
        result = new Gson().fromJson(output,AllUser.class);
        return result;
    }
}
