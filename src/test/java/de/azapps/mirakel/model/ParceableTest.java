package de.azapps.mirakel.model;

import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Parcel;

import com.google.common.base.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.azapps.mirakel.model.account.AccountMirakel;
import de.azapps.mirakel.model.file.FileMirakel;
import de.azapps.mirakel.model.list.ListMirakel;
import de.azapps.mirakel.model.list.SpecialList;
import de.azapps.mirakel.model.query_builder.CursorGetter;
import de.azapps.mirakel.model.recurring.Recurring;
import de.azapps.mirakel.model.semantic.Semantic;
import de.azapps.mirakel.model.tags.Tag;
import de.azapps.mirakel.model.task.Task;
import de.azapps.mirakelandroid.test.MirakelDatabaseTestCase;
import de.azapps.mirakelandroid.test.RandomHelper;
import de.azapps.mirakelandroid.BuildConfig;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ParceableTest extends MirakelDatabaseTestCase {

    @Test
    public void testTaskParcelable() {
        final ListMirakel list = new ListMirakel(RandomHelper.getRandomlong(),
                RandomHelper.getRandomString(),
                RandomHelper.getRandomSORT_BY(), RandomHelper.getRandomString(), RandomHelper.getRandomString(),
                RandomHelper.getRandomSYNC_STATE(), RandomHelper.getRandomint(), RandomHelper.getRandomint(),
                RandomHelper.getRandomint(), new AccountMirakel(RandomHelper.getRandomint(),
                        RandomHelper.getRandomString(),
                        RandomHelper.getRandomACCOUNT_TYPES(), RandomHelper.getRandomboolean(), Optional.<String>absent()),
                Optional.<Uri>absent());
        final Task task = new Task(RandomHelper.getRandomString(), list, RandomHelper.getRandomString(),
                                   RandomHelper.getRandomboolean(), RandomHelper.getRandomOptional_Calendar(),
                                   RandomHelper.getRandomPriority());
        final Parcel parcel = Parcel.obtain();
        task.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Task parceledTask = Task.CREATOR.createFromParcel(parcel);
        assertEquals(task, parceledTask);
    }

    @Test
    public void testTagParcelable() {
        final Tag tag = new Tag(RandomHelper.getRandomint(), RandomHelper.getRandomString(),
                                RandomHelper.getRandomint(), RandomHelper.getRandomboolean());
        final Parcel parcel = Parcel.obtain();
        tag.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final Tag parceledTag = Tag.CREATOR.createFromParcel(parcel);
        assertEquals(tag, parceledTag);
    }

    @Test
    public void testListParcelable() {
        final ListMirakel list = new ListMirakel(RandomHelper.getRandomlong(),
                RandomHelper.getRandomString(), RandomHelper.getRandomSORT_BY(), RandomHelper.getRandomString(),
                RandomHelper.getRandomString(), RandomHelper.getRandomSYNC_STATE(), RandomHelper.getRandomint(),
                RandomHelper.getRandomint(), RandomHelper.getRandomint(),
                new AccountMirakel(RandomHelper.getRandomint(), RandomHelper.getRandomString(),
                                   RandomHelper.getRandomACCOUNT_TYPES(), RandomHelper.getRandomboolean(), Optional.<String>absent()),
                Optional.<Uri>absent());
        final Parcel parcel = Parcel.obtain();
        list.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final ListMirakel parceledList = ListMirakel.CREATOR.createFromParcel(parcel);
        assertEquals(list, parceledList);
    }
    @Test
    public void testSemanticParcelable() {
        final Semantic semantic = new Semantic(RandomHelper.getRandomint(), RandomHelper.getRandomString(),
                                               RandomHelper.getRandomPriority(), RandomHelper.getRandomInteger(), Optional.<ListMirakel>absent(),
                                               RandomHelper.getRandomInteger());
        final Parcel parcel = Parcel.obtain();
        semantic.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final Semantic parceledSemantic = Semantic.CREATOR.createFromParcel(parcel);
        assertEquals(semantic, parceledSemantic);
    }

    @Test
    public void testRecurringParcelable() {
        final Recurring recurring = new Recurring(RandomHelper.getRandomlong(),
                RandomHelper.getRandomString(), RandomHelper.getRandomint(), RandomHelper.getRandomint(),
                RandomHelper.getRandomint(), RandomHelper.getRandomint(), RandomHelper.getRandomint(),
                RandomHelper.getRandomboolean(), RandomHelper.getRandomOptional_Calendar(),
                RandomHelper.getRandomOptional_Calendar(), RandomHelper.getRandomboolean(),
                RandomHelper.getRandomboolean(), RandomHelper.getRandomSparseBooleanArray(),
                RandomHelper.getRandomOptional_Long());
        final Parcel parcel = Parcel.obtain();
        recurring.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final Recurring parceledRecurring = Recurring.CREATOR.createFromParcel(parcel);
        assertEquals(recurring, parceledRecurring);
    }

    @Test
    public void testFileParcelable() {
        final Task t = RandomHelper.getRandomTask();
        final MatrixCursor c = new MatrixCursor(new String[] {ModelBase.ID, ModelBase.NAME, FileMirakel.TASK, FileMirakel.PATH});
        c.addRow(new Object[] {RandomHelper.getRandomInteger(), RandomHelper.getRandomString(), t.getId(), RandomHelper.getRandomUri()});
        c.moveToFirst();
        final FileMirakel fileMirakel = new FileMirakel(CursorGetter.unsafeGetter(c));
        final Parcel parcel = Parcel.obtain();
        fileMirakel.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final FileMirakel parceledFile = FileMirakel.CREATOR.createFromParcel(parcel);
        assertEquals(fileMirakel, parceledFile);
    }

    @Test
    public void testAccountParcelable() {
        final AccountMirakel account = new AccountMirakel(RandomHelper.getRandomint(),
                RandomHelper.getRandomString(), RandomHelper.getRandomACCOUNT_TYPES(),
                RandomHelper.getRandomboolean(), RandomHelper.getRandomOptional_String());
        final Parcel parcel = Parcel.obtain();
        account.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final AccountMirakel parceledAccount = AccountMirakel.CREATOR.createFromParcel(parcel);
        assertEquals(account, parceledAccount);
    }

    @Test
    public void testSpecialListParcelable() {
        MatrixCursor c = new MatrixCursor(new String[] {ModelBase.ID,
                                          ModelBase.NAME,
                                          SpecialList.WHERE_QUERY,
                                          SpecialList.ACTIVE,
                                          SpecialList.DEFAULT_LIST,
                                          SpecialList.DEFAULT_DUE,
                                          SpecialList.SORT_BY_FIELD,
                                          DatabaseHelper.SYNC_STATE_FIELD,
                                          SpecialList.COLOR,
                                          SpecialList.LFT,
                                          SpecialList.RGT,
                                          SpecialList.ICON_PATH
                                                       });
        int id = RandomHelper.getRandomint();
        if (id < 0) {
            id *= -1;
        }
        c.addRow(new Object[] {id, //ModelBase.ID,
                               RandomHelper.getRandomString(),//ModelBase.NAME
                               " ",//SpecialList.WHERE_QUERY
                               RandomHelper.getRandomboolean() ? 0 : 1, //SpecialList.ACTIVE
                               RandomHelper.getRandomint(),//SpecialList.DEFAULT_LIST,
                               RandomHelper.getRandomOptional_Integer().orNull(),//SpecialList.DEFAULT_DUE,
                               RandomHelper.getRandomSORT_BY().getShort(),//SpecialList.SORT_BY_FIELD,
                               RandomHelper.getRandomSYNC_STATE().toInt(),//DatabaseHelper.SYNC_STATE_FIELD,
                               RandomHelper.getRandomint(),//SpecialList.COLOR,
                               RandomHelper.getRandomint(),//SpecialList.LFT,
                               RandomHelper.getRandomint(),//SpecialList.RGT,
                               RandomHelper.getRandomOptional_Uri().orNull()
                              });//SpecialList.ICON_PATH
        c.moveToFirst();
        SpecialList special = new SpecialList(CursorGetter.unsafeGetter(c));

        final Parcel parcel = Parcel.obtain();
        special.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        final SpecialList parceledSpecial = SpecialList.CREATOR.createFromParcel(parcel);
        assertEquals(special, parceledSpecial);
    }

}
