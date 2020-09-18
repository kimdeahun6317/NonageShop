package nonageshop.dao;

import nonageshop.dto.Member;

public interface MemberDao {
	
	public int confirmID(String userid);
	
	public Member getMember(String id);
	
	public int insertMember(Member member);
}
