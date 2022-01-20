package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.danilorocha.app.R;
import com.danilorocha.app.model.Contato;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Contato> adapter;
    private ArrayList<Contato> contatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listaContatos);
        contatos = obterContatos();
        adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);
        listView.setAdapter(adapter);
    }//onCreate

    public ArrayList<Contato> obterContatos() {
        String[] projection = {
                ContactsContract.Data.MIMETYPE,
                ContactsContract.Data.CONTACT_ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_URI,
                ContactsContract.Contacts.STARRED,
                ContactsContract.RawContacts.ACCOUNT_TYPE,
                ContactsContract.CommonDataKinds.Contactables.DATA,
                ContactsContract.CommonDataKinds.Contactables.TYPE
        };

        String selection = ContactsContract.Data.MIMETYPE + " in (?, ?)" + " AND " +
                ContactsContract.Data.HAS_PHONE_NUMBER + " = '" + 1 + "'";

        String[] selectionArgs = {
                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE,
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
        };

        String sortOrder = ContactsContract.Contacts.SORT_KEY_ALTERNATIVE;

        Uri uri = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) ?
                ContactsContract.CommonDataKinds.Contactables.CONTENT_URI :
                ContactsContract.Data.CONTENT_URI;

        Cursor cursor = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        final int mimeTypeIdx = cursor.getColumnIndex(ContactsContract.Contacts.Data.MIMETYPE);
        final int idIdx = cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID);
        final int nameIdx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        Log.e("Contacts----", "ontacts__nameIdx----" + nameIdx);
        final int dataIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Contactables.DATA);
        final int typeIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Contactables.TYPE);
        final int photo = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Contactables.PHOTO_URI);
        final int account_type = cursor.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_TYPE);
        ArrayList<Contato> dados = new ArrayList<>();

        while (cursor.moveToNext()) {
            long id = cursor.getLong(idIdx);
            int type = cursor.getInt(typeIdx);
            String data = cursor.getString(dataIdx);
            String mimeType = cursor.getString(mimeTypeIdx);
            String photo_uri = cursor.getString(photo);
            String account_t = cursor.getString(account_type);

            Log.e("Contacts----", "Contacts----" + nameIdx + "---" + type + "---" + data + "----" + id);
            if (mimeType.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE))
                //For email
                Log.e("Contacts----", "Contacts-->>" + type + "---data---" + data + "--id--" + id);
            else
                Log.e("Contacts----", "Contacts--else>>" + type + "---data---" + data + "--id--" + id);

            dados.add(new Contato((int) id, type, data, mimeType, photo_uri, account_t));
        }//while
        cursor.close();
        return dados;
    }//metodo


}//classe