package net.notfab.lindsey.core.commands.fun;

import net.notfab.lindsey.framework.command.CommandDescriptor;
import net.notfab.lindsey.framework.command.Modules;
import net.notfab.lindsey.framework.command.help.HelpArticle;
import net.notfab.lindsey.framework.command.help.HelpPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RollTest {

    private Roll command;

    @BeforeEach
    void setUp() throws Exception {
        command = mock(Roll.class);
        when(command.execute(null, null, new String[0], null))
                .thenReturn(true);
        when(command.execute(null, null, new String[]{}, null))
                .thenReturn(true);
        when(command.getInfo()).thenCallRealMethod();
        when(command.help(null)).thenCallRealMethod();
    }

    @Test
    void getInfo() {
        CommandDescriptor info = command.getInfo();
        assertEquals("roll", info.getName(), "Name must be roll");
        assertEquals(Modules.FUN, info.getModule(), "Module must be roll");
        assertTrue(info.getPermissions().containsKey("commands." + info.getName()), "Must have permission with command name");
    }

    @Test
    void execute() throws Exception {
        assertTrue(command.execute(null, null, new String[0], null), "No arguments must execute");
        assertTrue(command.execute(null, null, new String[1], null), "");
    }

    @Test
    void help() throws Exception {
        HelpArticle article = command.help(null);
        HelpPage page = article.get("roll");
        assertNotNull(page, "Help page must not be null");
        assertEquals("commands." + command.getInfo().getName(), page.getPermission(), "Permission must be command name");
    }
}