package com.satyajeet.pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.satyajeet.pets.Data.PetsContract.PetEntry;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
//
//
//
//    //private PetDbHelper mDbHelper;
//
//    private RecyclerView mPetsRecyclerView;
//    private PetAdapter mPetAdapter;
//    private ArrayList<PetData> mPetArrayData;
//    private PetData mPetData;
//    private CursorAdapter mCursorAdapter;
//
//    private static final String LOG_TAG = CatalogActivity.class.getSimpleName();
//
//    private static final String[] pets_Projection = new String[]{
//            PetEntry._ID,
//            PetEntry.COLUMN_PET_NAME,
//            PetEntry.COLUMN_PET_BREED
//    };
//
//    private static final int PET_LOADER = 0;
//
//
//
//
////    @Override
////    protected void onStart() {
////        super.onStart();
////        displayDatabaseInfo();
////    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_catalog);
//
////        mPetsRecyclerView = findViewById(R.id.rec_view);
////
////        mPetArrayData = new ArrayList<>();
//
//
//
//        // Setup FAB to open EditorActivity
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        // Find the ListView which will be populated with the pet data
//        ListView petListView = (ListView) findViewById(R.id.list);
//
//        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
//        View emptyView = findViewById(R.id.empty_view);
//        petListView.setEmptyView(emptyView);
//
//        // Setup an Adapter to create a list item for each row of pet data in the Cursor.
//        // There is no pet data yet (until the loader finishes) so pass in null for the Cursor.
//        mCursorAdapter = new PetCursorAdapter(this, null);
//        petListView.setAdapter(mCursorAdapter);
//
//        // Setup the item click listener
//        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                // Create new intent to go to {@link EditorActivity}
//                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
//
//                // Form the content URI that represents the specific pet that was clicked on,
//                // by appending the "id" (passed as input to this method) onto the
//                // {@link PetEntry#CONTENT_URI}.
//                // For example, the URI would be "content://com.example.android.pets/pets/2"
//                // if the pet with ID 2 was clicked on.
//                Uri currentPetUri = ContentUris.withAppendedId(PetEntry.CONTENT_URI, id);
//
//                // Set the URI on the data field of the intent
//                intent.setData(currentPetUri);
//
//                // Launch the {@link EditorActivity} to display the data for the current pet.
//                startActivity(intent);
//            }
//        });
//
//        //getLoaderManager().initLoader(PET_LOADER, null,this);
//
//
//
//
//    }
//
//
//    /**
//     * Temporary helper method to display information in the onscreen TextView about the state of
//     * the pets database.
//     */
////    private void displayDatabaseInfo() {
////        // To access our database, we instantiate our subclass of SQLiteOpenHelper
////        // and pass the context, which is the current activity.
////        //PetDbHelper mDbHelper = new PetDbHelper(this);
////
////        // Create and/or open a database to read from it
////        //SQLiteDatabase db = mDbHelper.getReadableDatabase();
////
////        // Perform this raw SQL query "SELECT * FROM pets"
////        // to get a Cursor that contains all rows from the pets table.
//////        Cursor cursor = db.rawQuery("SELECT * FROM " + PetEntry.TABLE_NAME, null);
////
////        String[] projection = { PetEntry._ID,
////                                PetEntry.COLUMN_PET_NAME,
////                                PetEntry.COLUMN_PET_BREED,
////                                PetEntry.COLUMN_PET_GENDER,
////                                PetEntry.COLUMN_PET_WEIGHT };
////
//////        String selection = PetEntry.COLUMN_PET_GENDER + "=?";
//////        String []selectionArgs = new String[] { String.valueOf(PetEntry.GENDER_FEMALE) };
////
////        Cursor cursor = getContentResolver().query(PetEntry.CONTENT_URI, projection, null, null, null);
////        try {
////            // Display the number of rows in the Cursor (which reflects the number of rows in the
////            // pets table in the database).
//////            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
//////            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
//////            displayView.append(PetEntry._ID + " - " + PetEntry.COLUMN_PET_NAME + " - " + PetEntry.COLUMN_PET_BREED + " - " + PetEntry.COLUMN_PET_GENDER + " - " + PetEntry.COLUMN_PET_WEIGHT + "\n");
////
////            int idColumnIndex = cursor.getColumnIndex(PetEntry._ID);
////            int idNameIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
////            int idBreedIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);
////            int idGenderIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER);
////            int idWeightIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT);
////
////
////            while (cursor.moveToNext()){
////                int id = cursor.getInt(idColumnIndex);
////                String name = cursor.getString(idNameIndex);
////                String breed = cursor.getString(idBreedIndex);
////                int gender = cursor.getInt(idGenderIndex);
////                int weight = cursor.getInt(idWeightIndex);
////                mPetData = new PetData(name, breed);
////                mPetArrayData.add(mPetData);
////
////                //displayView.append("\n" + id + " - " + name + " - " + breed + " - " + gender + " - " + weight);
////            }
////
////            mPetAdapter = new PetAdapter(this, mPetArrayData);
////            mPetsRecyclerView.setAdapter(mPetAdapter);
////
////            LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
////            mPetsRecyclerView.setLayoutManager(layoutManager);
////
////
////        } finally {
////            // Always close the cursor when you're done reading from it. This releases all its
////            // resources and makes it invalid.
////            cursor.close();
////        }
////    }
//
//    private void insertPet(){
//        //SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//        // Create a ContentValues object where column names are the keys,
//        // and Toto's pet attributes are the values.
//        ContentValues values = new ContentValues();
//        values.put(PetEntry.COLUMN_PET_NAME, "Toto");
//        values.put(PetEntry.COLUMN_PET_BREED, "Terrier");
//        values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
//        values.put(PetEntry.COLUMN_PET_WEIGHT, 7);
//
//        // Insert a new row for Toto in the database, returning the ID of that new row.
//        // The first argument for db.insert() is the pets table name.
//        // The second argument provides the name of a column in which the framework
//        // can insert NULL in the event that the ContentValues is empty (if
//        // this is set to "null", then the framework will not insert a row when
//        // there are no values).
//        // The third argument is the ContentValues object containing the info for Toto.
//        Uri newUri = getContentResolver().insert(PetEntry.CONTENT_URI, values);
//
//
//
//        // Log.e(LOG_TAG, "New Row ID: " + newRowId);
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu options from the res/menu/menu_catalog.xml file.
//        // This adds menu items to the app bar.
//        getMenuInflater().inflate(R.menu.menu_catalog, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // User clicked on a menu option in the app bar overflow menu
//        switch (item.getItemId()) {
//            // Respond to a click on the "Insert dummy data" menu option
//            case R.id.action_insert_dummy_data:
//                insertPet();
//                //displayDatabaseInfo();
//                return true;
//            // Respond to a click on the "Delete all entries" menu option
//            case R.id.action_delete_all_entries:
//                // Do nothing for now
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @NonNull
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
//
//
//        return new CursorLoader(this, PetEntry.CONTENT_URI, pets_Projection, null, null, null);
//    }
//
//    @Override
//    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
//        mCursorAdapter.swapCursor(data);
//    }
//
//    @Override
//    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
//        mCursorAdapter.swapCursor(null);
//    }
//}

public class CatalogActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    /** Identifier for the pet data loader */
    private static final int PET_LOADER = 0;

    /** Adapter for the ListView */
    PetCursorAdapter mCursorAdapter;

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

        // Find the ListView which will be populated with the pet data
        ListView petListView = (ListView) findViewById(R.id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        petListView.setEmptyView(emptyView);

        // Setup an Adapter to create a list item for each row of pet data in the Cursor.
        // There is no pet data yet (until the loader finishes) so pass in null for the Cursor.
        mCursorAdapter = new PetCursorAdapter(this, null);
        petListView.setAdapter(mCursorAdapter);

        // Setup the item click listener
        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create new intent to go to {@link EditorActivity}
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);

                // Form the content URI that represents the specific pet that was clicked on,
                // by appending the "id" (passed as input to this method) onto the
                // {@link PetEntry#CONTENT_URI}.
                // For example, the URI would be "content://com.example.android.pets/pets/2"
                // if the pet with ID 2 was clicked on.
                Uri currentPetUri = ContentUris.withAppendedId(PetEntry.CONTENT_URI, id);

                // Set the URI on the data field of the intent
                intent.setData(currentPetUri);

                // Launch the {@link EditorActivity} to display the data for the current pet.
                startActivity(intent);
            }
        });

        // Kick off the loader
        getSupportLoaderManager().initLoader(PET_LOADER, null, this);
    }

    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertPet() {
        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(PetEntry.COLUMN_PET_NAME, "Toto");
        values.put(PetEntry.COLUMN_PET_BREED, "Terrier");
        values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
        values.put(PetEntry.COLUMN_PET_WEIGHT, 7);

        // Insert a new row for Toto into the provider using the ContentResolver.
        // Use the {@link PetEntry#CONTENT_URI} to indicate that we want to insert
        // into the pets database table.
        // Receive the new content URI that will allow us to access Toto's data in the future.
        Uri newUri = getContentResolver().insert(PetEntry.CONTENT_URI, values);
    }

    /**
     * Helper method to delete all pets in the database.
     */
    private void deleteAllPets() {
        int rowsDeleted = getContentResolver().delete(PetEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from pet database");
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
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllPets();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                PetEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update {@link PetCursorAdapter} with this new cursor containing updated pet data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);
    }
}