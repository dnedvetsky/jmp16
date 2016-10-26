import backend.NoteEntry;
import frontend.NotebookHelper;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Dovashar on 25.10.2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    @Mock
    NoteEntry noteEntryMockery;
    @Mock
    NotebookHelper notebookHelperMockery;

    @Before
    public void setUp() {
        NotebookHelper helper = new NotebookHelper();
        helper.deleteAllNotes();
        helper.addNote("tag", "tag");
        helper.buildSearchCondition("tag", "tag").printNotesViaSearch();
    }

    @Test
    public void testResult() {
        when(noteEntryMockery.getText()).thenReturn("text");
        assertEquals("Verifying mockito", noteEntryMockery.getText(), new NoteEntry("10/10/2010", "tag", "text").getText());
        when(noteEntryMockery.getEntry())
                .thenReturn(new Document().append("timestamp", "10/10/2015").append("tag", "text").append("text", "text"));

        assertEquals(noteEntryMockery.getEntry(), new NoteEntry("10/10/2015", "text", "text").getEntry());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        when(notebookHelperMockery.printAndSearchNotesByTag("tag"))
                .thenReturn("Tag: tag, Text: tag");

        assertEquals(notebookHelperMockery.printAndSearchNotesByTag("tag"),
                new NotebookHelper().buildSearchCondition("tag", "tag").printNotesViaSearch());
    }


}
