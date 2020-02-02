package jutildfc.interfaces;

import com.documentum.fc.methodserver.DfMethodArgumentException;
import com.documentum.fc.methodserver.DfStandardJobArguments;
import com.documentum.fc.methodserver.IDfMethodArgumentManager;

public class UIDfMethodJobArgs extends DfStandardJobArguments
{
    public UIDfMethodJobArgs(IDfMethodArgumentManager methodArgumentManager) throws DfMethodArgumentException
    {
	super(methodArgumentManager);
    }
}
