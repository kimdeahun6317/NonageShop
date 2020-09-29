package nonageshop.service;

import java.util.ArrayList;

import nonageshop.dao.QnADao;
import nonageshop.dao.impl.QnADaoImpl;
import nonageshop.dto.QnA;

public class QnAService {
	private QnADao dao = QnADaoImpl.getInstance();

	public ArrayList<QnA> listQna(String id) {
		return dao.listQna(id);
	};

	public QnA getQnA(int no) {
		return dao.getQnA(no);
	};

	public int insertqna(QnA qna) {
		return dao.insertQna(qna);
	};
}
