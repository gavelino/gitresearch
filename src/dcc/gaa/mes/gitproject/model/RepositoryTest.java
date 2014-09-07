package dcc.gaa.mes.gitproject.model;
import java.util.List;

import javax.persistence.*;

import org.eclipse.egit.github.core.RepositoryCommit;
@Entity
//@Table(name="msgteste", schema="public")
public class RepositoryTest{
	@Id
    @GeneratedValue
    @Column(name="persist_id")
    private int id; 

    @Column(name="persist_desc")
	String desc;
    

    @Column(name="persist_commits")
    @ElementCollection
    List<String> commits;
    
	int size;
	
	
	public RepositoryTest(String desc, List<String> commits) {
		this.desc = desc;
		this.commits = commits;
		this.size = commits.size();
	}

//    List<RepositoryCommit> commits;
//	int size;
//	public RepositoryTest(String desc, List<RepositoryCommit> commits) {
//		this.desc = desc;
//		this.commits = commits;
//		this.size = commits.size();
//	}
	
	@Override
	public String toString() {
		return  desc + " "+size;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<String> getCommits() {
		return commits;
	}

	public void setCommits(List<String> commits) {
		this.commits = commits;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getId() {
		return id;
	}
}