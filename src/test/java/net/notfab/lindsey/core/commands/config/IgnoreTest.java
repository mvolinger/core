package net.notfab.lindsey.core.commands.config;

import net.notfab.lindsey.core.framework.command.CommandDescriptor;
import net.notfab.lindsey.core.framework.command.Modules;
import net.notfab.lindsey.core.framework.command.help.HelpArticle;
import net.notfab.lindsey.core.framework.command.help.HelpPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IgnoreTest {

    private Ignore command;

    @BeforeEach
    void setUp() {
        command = mock(Ignore.class);
        when(command.getInfo())
            .thenCallRealMethod();
        when(command.help(any()))
            .thenCallRealMethod();
    }

    @Test
    void getInfo() {
        CommandDescriptor info = command.getInfo();
        assertEquals("ignore", info.getName(), "Name must be ignore");
        assertEquals(Modules.CORE, info.getModule(), "Module must be core");
        assertTrue(info.getPermissions().stream()
            .anyMatch(perm -> perm.getName().equals("commands." + info.getName())), "Must have permission with command name");
    }

    @Test
    void help() {
        HelpArticle article = command.help(null);
        HelpPage page = article.get("ignore");
        assertNotNull(page, "Help page must not be null");
        assertEquals("commands." + command.getInfo().getName(), page.getPermission(), "Permission must be command name");
    }

}
