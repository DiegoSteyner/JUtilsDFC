package jutildfc.utilsdfc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfVersionPolicy;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.DfValue;

import jutildfc.interfaces.AbstractUtilDfc;

public class UIdfSysObject extends AbstractUtilDfc
{
    /**
     * Construtor Parametrizado
     * 
     * @param session O Objeto {@link UIdfSession} configurado
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public UIdfSysObject(jutildfc.utilsdfc.UidfSession session)
    {
        super(session);
    }

    public static final String DESTROY_ALL_VERSION  = "all";
    public static final String DESTROY_THIS_VERSION = "current";

    /**
     * Metodo que deleta um objeto do repositorio
     * 
     * @param objId O id do Objeto que se deseja deletar
     * @param version	<blockquote>UIdfSysObject.DESTROY_ALL_VERSION</blockquote>
     * 			<blockquote>UIdfSysObject.DESTROY_THIS_VERSION</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void destroyObject(String objId, String version) throws Exception
    {
        if (version.equals(DESTROY_ALL_VERSION))
        {
            getUidfSession().getIdfSysObject(objId).destroyAllVersions();
        }
        else if (version.equals(DESTROY_THIS_VERSION))
        {
            getUidfSession().getIdfSysObject(objId).destroy();
        }

    }

    /**
     * Metodo que retorna a extensao de um Objeto
     * 
     * @param objIdPath O r_object_id ou o Path do Objeto
     * 
     * @return A Extensao do arquivo
     *  
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String getExtensionObject(String objIdPath) throws Exception
    {
        IDfSysObject obj = getUidfSession().getIdfSysObject(objIdPath);

        return (getUidfSession().getSession().getFormat(obj.getContentType()).getDOSExtension().toString());
    }

    /**
     * Metodo que testa se um objeto e de um determinado tipo
     * 
     * @param obj O Objeto que se deseja testar
     * @param typeName O nome do tipo que se deseja verificar
     * 
     * @return	<blockquote>true, E do tipo passado</blockquote>
     * 			<blockquote>false, Nao e do tipo passado</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean isType(IDfSysObject obj, String typeName) throws Exception
    {
        return (obj.getType().isSubTypeOf(typeName));
    }

    /**
     * Metodo que escreve um novo conteudo em um arquivo
     * 
     * @param objIdPath O r_object_id ou o Path do objeto
     * @param file O Arquivo onde o conteudo esta
     * 
     * @return Se True, conteúdo configurado com sucesso.
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean setContent(String objIdPath, String file) throws Exception
    {
        IDfSysObject obj = getUidfSession().getIdfSysObject(objIdPath);

        if (obj.isCheckedOut())
        {
            obj.setContent(getByteArrayOutputStream(file));
            obj.save();

            return (true);
        }
        else
        {
            System.out.println("O Objeto [ ".concat(objIdPath).concat(" ] nao esta em checkout"));
            return (false);
        }
    }

    /**
     * Metodo que mostra todas as versoes de um objeto com os seus Respectivos Ids
     * 
     * @param objId O Id do objeto
     * @param printThisId <blockquote>true, Imprimir o Id passado por parametro</blockquote>
     * 					  <blockquote>false, Nao imprimir o Id passado por parametro</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void displayAllVersion(String objId, boolean printThisId) throws Exception
    {
        IDfSysObject sysObj = getUidfSession().getIdfSysObject(objId);
        int isCurrent = 0;

        while (DfId.isObjectId(sysObj.getAntecedentId().getId()))
        {
            isCurrent = sysObj.findValue("r_version_label", new DfValue("CURRENT"));
            if (printThisId)
            {
                if (isCurrent == -1)
                {
                    System.out.println(sysObj.getVersionLabel(0).concat(" => [").concat(sysObj.getObjectId().getId()).concat("]"));
                }
                else
                {
                    System.out.println(sysObj.getVersionLabel(0).concat(" => [").concat(sysObj.getObjectId().getId()).concat("]").concat(" - [CURRENT]"));
                }
            }
            else if (!sysObj.getObjectId().getId().equals(objId))
            {
                if (isCurrent == -1)
                {
                    System.out.println(sysObj.getVersionLabel(0).concat(" => [").concat(sysObj.getObjectId().getId()).concat("]"));
                }
                else
                {
                    System.out.println(sysObj.getVersionLabel(0).concat(" => [").concat(sysObj.getObjectId().getId()).concat("]").concat(" - [CURRENT]"));
                }

            }
            sysObj = getUidfSession().getIdfSysObject(sysObj.getAntecedentId().getId());
        }
    }

    /**
     * Metodo que mostra todos os VersionLabels de um Objeto
     * 
     * @param objId O id do Objeto
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void displayVersionLabelsObject(String objId) throws Exception
    {
        IDfSysObject sysObj = getUidfSession().getIdfSysObject(objId);

        for (int i = 0; i < sysObj.getVersionLabelCount(); i++)
        {
            System.out.println(sysObj.getVersionLabel(i));
        }
    }

    /**
     * Metodo que mostra as informacoes de um Objeto
     * 
     * @param Obj O Objeto que se deseja mostrar as informacoes
     * @param basic <blockquote>true, Imprimir as informacoes basicas</blockquote>
     * 				<blockquote>false, Imprimir o dump() do objeto</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void displayObjectInfo(IDfSysObject Obj, boolean basic) throws Exception
    {
        if (basic)
        {
            System.out.println("ID           : ".concat(Obj.getObjectId().getId()));
            System.out.println("Name         : ".concat(Obj.getObjectName()));
            System.out.println("Owner        : ".concat(Obj.getOwnerName()));
            System.out.println("Created      : ".concat(Obj.getCreationDate().asString(Obj.getCreationDate().getPattern())));
            System.out.println("Subject      : ".concat(Obj.getSubject()));
            System.out.println("Content Type : ".concat(Obj.getContentType()));
        }
        else
        {
            System.out.println(Obj.dump());
        }
    }

    /**
     * Metodo que mostra as informacoes da versao do objeto
     * 
     * @param objId O r_object_id ou o Path
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void displayVersionInfo(String objId) throws Exception
    {

        IDfVersionPolicy verPolObj = getUidfSession().getIdfSysObject(objId).getVersionPolicy();

        System.out.println("Version Atual     : ".concat(verPolObj.getSameLabel()));
        System.out.println("Nxt minor         : ".concat(verPolObj.getNextMinorLabel()));
        System.out.println("Nxt major         : ".concat(verPolObj.getNextMajorLabel()));

        if (verPolObj.getDefaultCheckinVersion() == IDfVersionPolicy.DF_NEXT_MAJOR)
        {
            System.out.println("Default label : DF_NEXT_MAJOR");
        }
        else if (verPolObj.getDefaultCheckinVersion() == IDfVersionPolicy.DF_NEXT_MINOR)
        {
            System.out.println("Default label     : DF_NEXT_MINOR");
        }
        else if (verPolObj.getDefaultCheckinVersion() == IDfVersionPolicy.DF_SAME_VERSION)
        {
            System.out.println("Default label : DF_SAME_VERSION");
        }
        else if (verPolObj.getDefaultCheckinVersion() == IDfVersionPolicy.DF_BRANCH_VERSION)
        {
            System.out.println("Default label : DF_BRANCH_VERSION");
        }

        System.out.println("Vers summary      : ".concat(verPolObj.getVersionSummary(";")));
        System.out.println("Log comment       : ".concat(verPolObj.getLogComment()));
    }

    public ByteArrayOutputStream getByteArrayOutputStream(String file) throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream in = new FileInputStream(new File(file));

        byte[] buffer = new byte[4096];
        int len;

        while ((len = in.read(buffer)) >= 0)
        {
            baos.write(buffer, 0, len);
        }
        in.close();
        baos.flush();

        return (baos);
    }
}
