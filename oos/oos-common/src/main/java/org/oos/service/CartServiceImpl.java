package org.oos.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.oos.domain.CartVO;
import org.oos.mapper.CartMapper;
import org.oos.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class CartServiceImpl implements CartService {
	@Setter(onMethod_=@Autowired)
	private CartMapper cartMapper;
	@Setter(onMethod_=@Autowired)
	private ProductMapper productMapper;

	@Override
    public List<CartVO> getList(Map<String, Object> map) {

        return cartMapper.getListByMid(map);
    }

	@Override
	@Transactional
	public CartVO get(Long cno) {

		return cartMapper.getByCno(cno);
	}

	@Override
	public int register(List<CartVO> list) {
		
		list.forEach(vo -> {
			CartVO cart = cartMapper.getByOpno(vo.getOpno());
			log.info(cart+"");
			if(cart != null) {
				Long qty = cart.getQty() + vo.getQty();
				cart.setQty(qty);
				cartMapper.update(cart);
			}else {
				cartMapper.insert(vo);
			}
			
		});
		
		return 1;
	}

	@Override
	public int modify(CartVO vo) {
		return cartMapper.modify(vo);
	}

	@Override
	public int remove(Long cno) {
		return cartMapper.delete(cno);
	}

	@Override
	public int count(Map<String, Object> map) {
		return cartMapper.count(map);
	}

}
