package com.satyajeet.pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.satyajeet.pets.Data.PetsContract.PetEntry;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.satyajeet.pets.Data.PetDbHelper;

public class CatalogActivity extends AppCompatActivity {

    //private PetDbHelper mDbHelper;

    private static final String LOG_TAG = CatalogActivity.class.getSimpleName();

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });




    }


    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        //PetDbHelper mDbHelper = new PetDbHelper(this);

        // Create and/or open a database to read from it
        //SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
//        Cursor cursor = db.rawQuery("SELECT * FROM " + PetEntry.TABLE_NAME, null);

        String[] projection = { PetEntry._ID,
                                PetEntry.COLUMN_PET_NAME,
                                PetEntry.COLUMN_PET_BREED,
                                PetEntry.COLUMN_PET_GENDER,
                                PetEntry.COLUMN_PET_WEIGHT };

//        String selection = PetEntry.COLUMN_PET_GENDER + "=?";
//        String []selectionArgs = new String[] { String.valueOf(PetEntry.GENDER_FEMALE) };

        Cursor cursor = getContentResolver().query(PetEntry.CONTENT_URI, projection, null, null, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(PetEntry._ID + " - " + PetEntry.COLUMN_PET_NAME + " - " + PetEntry.COLUMN_PET_BREED + " - " + PetEntry.COLUMN_PET_GENDER + " - " + PetEntry.COLUMN_PET_WEIGHT + "\n");

            int idColumnIndex = cursor.getColumnIndex(PetEntry._ID);
            int idNameIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
            int idBreedIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);
            int idGenderIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER);
            int idWeightIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT);


            while (cursor.moveToNext()){
                int id = cursor.getInt(idColumnIndex);
                String name = cursor.getString(idNameIndex);
                String breed = cursor.getString(idBreedIndex);
                int gender = cursor.getInt(idGenderIndex);
                int weight = cursor.getInt(idWeightIndex);

                displayView.append("\n" + id + " - " + name + " - " + breed + " - " + gender + " - " + weight);
            }


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertPet(){
        //SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(PetEntry.COLUMN_PET_NAME, "Toto");
        values.put(PetEntry.COLUMN_PET_BREED, "Terrier");
        values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
        values.put(PetEntry.COLUMN_PET_WEIGHT, 7);

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        Uri newUri = getContentResolver().insert(PetEntry.CONTENT_URI, values);



        // Log.e(LOG_TAG, "New Row ID: " + newRowId);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}