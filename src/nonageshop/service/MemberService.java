package nonageshop.service;

import nonageshop.dao.MemberDao;
import nonageshop.dao.impl.MemberDaoImpl;
import nonageshop.dto.Member;

public class MemberService {
	MemberDao dao = MemberDaoImpl.getInstance();
	
	public int confirmID(String userid) {
		return dao.confirmID(userid);
	}
	
	public Member getMember(String id) {
		return dao.getMember(id);
	}
	
	public int insertMember(Member member) {
		return dao.insertMember(member);
	}
}
