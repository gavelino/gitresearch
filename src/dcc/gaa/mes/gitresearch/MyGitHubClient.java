package dcc.gaa.mes.gitresearch;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GitHubRequest;
import org.eclipse.egit.github.core.client.GitHubResponse;

import dcc.gaa.mes.gitresearch.util.GitHubUtil;

public class MyGitHubClient extends GitHubClient{
	private Queue<GitHubClient> clients = new LinkedList<GitHubClient>();
	private GitHubClient client;
	private Map<GitHubClient, String> tokenMap;
	
	public MyGitHubClient(Queue<GitHubClient> clients,  Map<GitHubClient, String> tokenMap) {
		super();
		this.clients = clients;
		this.client = clients.poll();
		this.clients.add(this.client);
		this.tokenMap = tokenMap;
	}
	
		
	@Override
	public GitHubResponse get(GitHubRequest request) throws IOException {
		return getClient().get(request);
	}
	
	
	
	
	@Override
	public GitHubClient setSerializeNulls(boolean serializeNulls) {
		
		return getClient().setSerializeNulls(serializeNulls);
	}


	@Override
	public GitHubClient setUserAgent(String agent) {
		
		return getClient().setUserAgent(agent);
	}


	@Override
	public GitHubClient setCredentials(String user, String password) {
		
		return getClient().setCredentials(user, password);
	}


	@Override
	public GitHubClient setOAuth2Token(String token) {
		
		return getClient().setOAuth2Token(token);
	}


	@Override
	public GitHubClient setBufferSize(int bufferSize) {
		
		return getClient().setBufferSize(bufferSize);
	}


	@Override
	public String getUser() {
		
		return getClient().getUser();
	}


	@Override
	public void post(String uri) throws IOException {
		
		getClient().post(uri);
	}


	@Override
	public void put(String uri) throws IOException {
		
		getClient().put(uri);
	}


	@Override
	public void delete(String uri) throws IOException {
		
		getClient().delete(uri);
	}


	@Override
	public InputStream getStream(GitHubRequest request) throws IOException {
		
		return getClient().getStream(request);
	}


	@Override
	public InputStream postStream(String uri, Object params) throws IOException {
		
		return getClient().postStream(uri, params);
	}


	@Override
	public <V> V post(String uri, Object params, Type type) throws IOException {
		
		return getClient().post(uri, params, type);
	}


	@Override
	public <V> V put(String uri, Object params, Type type) throws IOException {
		
		return getClient().put(uri, params, type);
	}


	@Override
	public void delete(String uri, Object params) throws IOException {
		
		getClient().delete(uri, params);
	}


	@Override
	public int getRemainingRequests() {
		
		return getClient().getRemainingRequests();
	}


	@Override
	public int getRequestLimit() {
		
		return getClient().getRequestLimit();
	}


	public GitHubClient getClient() {
		if (client.getRemainingRequests()==-1)
			return this.client;
		if (client.getRemainingRequests()<1){
			String oldClientName = client.getUser();
			if (clients.peek().getRemainingRequests()>=1){
				this.client = clients.poll();
				clients.add(this.client);
				System.out.println("Cliente " +oldClientName+ " alterado. Novo cliente "+client.getUser());
			}
			else{
				if (clients.peek().getRemainingRequests()==0){
					GitHubClient client = clients.poll();
					clients.add(client);
					return client;
				}
				if (clients.peek().getRemainingRequests()==0){

					try {
						Date resetDate = GitHubUtil.getResetTime(tokenMap.get(clients.peek()));
						long waitTime = resetDate.getTime() - new Date().getTime()+5000;

						SimpleDateFormat formata = new SimpleDateFormat("HH:mm:ss");
						System.out.println("Aguardando novos limites de uso da API");
						System.out.println("Time = " + formata.format(new Date()) + "    " + waitTime);
						Thread.sleep(waitTime);
						GitHubClient client = clients.poll();
						clients.add(client);
						return client;
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
		return this.client;
	}
}
