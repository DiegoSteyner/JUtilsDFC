package jutildfc.utilsjava;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Classe estatic que adiciona dinamicamente arquivos ao ClassPath do programa<br/>
 * 
 * Limitacoes:
 * <ul>
 * 	<li>Nao vai trocar itens existentes, apenas adicionalos</li>
 * 	<li>Trabalha somente com URLClassLoaders</li>
 * </ul>
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation"})
public final class ClassPathUtil 
{
    private static final Class[] parametros = new Class[] { URL.class };

    /**
     * Construtor Privado
     */
    private ClassPathUtil()
    {
    }

    /**
     * Adiciona um arquivo ao ClassPath do programa
     * 
     * @param caminho O caminho do arquivo
     * 
     * @see #addArquivo(File)
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static void addFile(String caminho) throws Exception
    {
	addFile(new File(caminho));
    }

    /**
     * Adiciona um arquivo ao ClassPath do programa
     * 
     * @param caminho O caminho do arquivo
     * 
     * @see #addURL(URL)
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static void addFile(File caminho) throws Exception
    {
	addURL(caminho.toURL());
    }

    /**
     * Adiciona uma url ao ClassPath do programa
     * 
     * @param url A URL do arquivo a ser adicionada
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static void addURL(URL url) throws Exception
    {
	URLClassLoader sysLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
	Class sysClass = URLClassLoader.class;

	try
	{
	    Method metodo = sysClass.getDeclaredMethod("addURL", parametros);
	    metodo.setAccessible(true);
	    metodo.invoke(sysLoader, new Object[] { url });
	} 
	catch (Throwable t)
	{
	    throw new Exception("Error, Url nao pode ser adicionada ao ClassPath: " + url);
	}
    }

    /**
     * Metodo que verifica se um arquivo esta ou nao adicionado no
     * ClassPath da aplicacao
     * 
     * @param arquivo O Arquivo a ser verificado
     * 
     * @return	<blockquote>true, Esta adicionado</blockquote>
     * 			<blockquote>false, Nao esta adicionado</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static boolean isAddNoClassPath(String arquivo) throws Exception
    {
	return (new ArrayList<File>(Arrays.asList(getClassPath())).contains(new File(arquivo)));
    }

    /**
     * Metodo que retorna todos os arquivos adicionados no classPath
     * da aplicacao
     * 
     * @return Um vetor de arquivos do ClassPath
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static File[] getClassPath() throws Exception
    {
	URL urls[] = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
	ArrayList<File> files = new ArrayList();

	for (URL url : urls)
	{
	    files.add(new File(url.toURI()));
	}
	return (files.toArray(new File[files.size()]));
    }
}
