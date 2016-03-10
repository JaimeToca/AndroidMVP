package com.jaime.toca.MVPActors.presentation;

import android.app.Fragment;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.jaime.toca.MVPActors.R;
import com.jaime.toca.MVPActors.ui.activities.ActorsPopularActivity;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Created by Jaime on 16/09/2015.
 */

public class ActorsPopularActivityTest extends ActivityInstrumentationTestCase2<ActorsPopularActivity> {

    private ActorsPopularActivity actorsPopularActivity;

    public ActorsPopularActivityTest() {
        super(ActorsPopularActivity.class);
    }

    @Override protected void setUp() throws Exception {
        super.setUp();
        actorsPopularActivity = getActivity();
    }

    @Override protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testContainsUserListFragment() {
        Fragment userListFragment =
                actorsPopularActivity.getFragmentManager().findFragmentById(R.id.fragmentUserList);
        assertThat(userListFragment, is(notNullValue()));
    }

}
