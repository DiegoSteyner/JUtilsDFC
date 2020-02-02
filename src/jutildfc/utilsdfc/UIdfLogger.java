package jutildfc.utilsdfc;

import java.io.PrintWriter;
import java.io.Writer;

import com.documentum.fc.common.DfLogger;

public class UIdfLogger
{
    private static final int DEBUG       = 0;
    private static final int INFO        = 1;
    private static final int ERROR       = 2;
    private static final int WARN        = 3;
    private static final int FATAL       = 4;
    private static final int TRACE       = 5;

    private PrintWriter      printWriter = null;
    private Writer           writer      = null;

    public UIdfLogger(Writer writer)
    {
        this.writer = writer;
    }

    public UIdfLogger(PrintWriter printWriter)
    {
        this.printWriter = printWriter;
    }

    public void writeDebug(String message) throws Exception
    {
        writeLog(DEBUG, message, null);
    }

    public void writeInfo(String message) throws Exception
    {
        writeLog(INFO, message, null);
    }

    public void writeTrace(String message) throws Exception
    {
        writeLog(TRACE, message, null);
    }

    public void writeError(String message, Exception e) throws Exception
    {
        writeLog(ERROR, message, e);
    }

    public void writeWarn(String message, Exception e) throws Exception
    {
        writeLog(WARN, message, e);
    }

    public void writeFatal(String message, Exception e) throws Exception
    {
        writeLog(FATAL, message, e);
    }

    private void writeLog(int level, String logMessage, Exception e) throws Exception
    {
        String message = logMessage;
        if (message != null)
        {
            if (level == DEBUG)
            {
                DfLogger.debug(this.getClass(), message, null, null);
            }
            else if (level == INFO)
            {
                DfLogger.info(this.getClass(), message, null, null);
            }
            else if (level == WARN)
            {
                DfLogger.warn(this.getClass(), message, null, null);
            }
            else if (level == FATAL)
            {
                DfLogger.fatal(this.getClass(), message, null, null);
            }
            else if (level == ERROR)
            {
                DfLogger.error(this.getClass(), message, null, e);
            }
            else if (level == TRACE)
            {
                DfLogger.trace(this.getClass(), message, null, e);
            }

            printLn(message);
        }
    }

    private void printLn(String message) throws Exception
    {
        if (getWriter() != null)
        {

        }
        else if (getPrintWriter() != null)
        {
            getPrintWriter().println(message);
        }
    }

    public Writer getWriter()
    {
        return writer;
    }

    public void setWriter(Writer writer)
    {
        this.writer = writer;
    }

    public PrintWriter getPrintWriter()
    {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter)
    {
        this.printWriter = printWriter;
    }
}
