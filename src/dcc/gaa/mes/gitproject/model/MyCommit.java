package dcc.gaa.mes.gitproject.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.eclipse.egit.github.core.Commit;

//import org.eclipse.egit.github.core.Tree;
@Entity
public class MyCommit {

	@Id
    @GeneratedValue
    private int id; 
	
	private MyCommitUser author;

	private MyCommitUser committer;

	private int commentCount;

	@OneToMany(cascade={CascadeType.ALL})
    private List<MyCommit> parents;

	private String message;

	private String sha;

	private String url;
// TODO Avaliar necessidade desse atributo 
//	private Tree tree;

	public MyCommit(Commit commit) {
		Method[] gettersAndSetters = commit.getClass().getMethods();

        for (int i = 0; i < gettersAndSetters.length; i++) {
                String methodName = gettersAndSetters[i].getName();
                try{
                  if(methodName.startsWith("get")&& !methodName.equalsIgnoreCase("getTree")){
                     this.getClass().getMethod(methodName.replaceFirst("get", "set") , gettersAndSetters[i].getReturnType() ).invoke(this, gettersAndSetters[i].invoke(commit, null));
                        }else if(methodName.startsWith("is") ){
                            this.getClass().getMethod(methodName.replaceFirst("is", "set") ,  gettersAndSetters[i].getReturnType()  ).invoke(this, gettersAndSetters[i].invoke(commit, null));
                        }

                }catch (NoSuchMethodException e) {
                    // TODO: handle exception
                }catch (IllegalArgumentException e) {
                    // TODO: handle exception
                } catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

        }
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return author
	 */
	public MyCommitUser getAuthor() {
		return author;
	}

	/**
	 * @param author
	 * @return this commit
	 */
	public MyCommit setAuthor(MyCommitUser author) {
		this.author = author;
		return this;
	}

	/**
	 * @return committer
	 */
	public MyCommitUser getCommitter() {
		return committer;
	}

	/**
	 * @param committer
	 * @return this commit
	 */
	public MyCommit setCommitter(MyCommitUser committer) {
		this.committer = committer;
		return this;
	}

	/**
	 * @return commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount
	 * @return this commit
	 */
	public MyCommit setCommentCount(int commentCount) {
		this.commentCount = commentCount;
		return this;
	}

	/**
	 * @return parents
	 */
	public List<MyCommit> getParents() {
		return parents;
	}

	/**
	 * @param parents
	 * @return this commit
	 */
	public MyCommit setParents(List<MyCommit> parents) {
		this.parents = parents;
		return this;
	}

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 * @return this commit
	 */
	public MyCommit setMessage(String message) {
		this.message = message;
		return this;
	}

	/**
	 * @return sha
	 */
	public String getSha() {
		return sha;
	}

	/**
	 * @param sha
	 * @return this commit
	 */
	public MyCommit setSha(String sha) {
		this.sha = sha;
		return this;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 * @return this commit
	 */
	public MyCommit setUrl(String url) {
		this.url = url;
		return this;
	}

//	/**
//	 * @return tree
//	 */
//	public Tree getTree() {
//		return tree;
//	}
//
//	/**
//	 * @param tree
//	 * @return this commit
//	 */
//	public MyCommit setTree(Tree tree) {
//		this.tree = tree;
//		return this;
//	}
}