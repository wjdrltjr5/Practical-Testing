package sample.cafekiosk;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sample.cafekiosk.unit.CafeKiosk;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class CafekioskTest {

	@Test
	void add_manual_test() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		cafeKiosk.add(new Americano());

		System.out.println(">> 담긴 음료 수 : " + cafeKiosk.getBeverages().size());
		System.out.println(">> 담긴 음료 : " + cafeKiosk.getBeverages().get(0).getName());
	}

	@Test
	void add() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		cafeKiosk.add(new Americano());

		assertThat(cafeKiosk.getBeverages()).hasSize(1);
		assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
	}


	@Test
	@DisplayName("여러개가 들어가는지 확인")
	void addSeveralBeverages() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();
		cafeKiosk.add(americano,2);

		assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
		assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
		assertThat(cafeKiosk.getBeverages()).hasSize(2);
		assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
	}
	@Test
	void addZeroBeverages() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();


		assertThatThrownBy(() -> cafeKiosk.add(americano,0))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
	}

	@Test
	void remove() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();

		cafeKiosk.add(americano);
		assertThat(cafeKiosk.getBeverages()).hasSize(1);

		cafeKiosk.remove(americano);
		assertThat(cafeKiosk.getBeverages()).hasSize(0);
	}

	@Test
	void clear() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		Latte latte = new Latte();
		Americano americano = new Americano();

		cafeKiosk.add(americano);
		cafeKiosk.add(latte);

		assertThat(cafeKiosk.getBeverages()).hasSize(2);

		cafeKiosk.clear();
		assertThat(cafeKiosk.getBeverages()).isEmpty();
	}

	@Test
	@DisplayName("가게 운영시간 안에만 성공하는 테스트")
	void createOrder() {

		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();

		cafeKiosk.add(americano);

		Order order = cafeKiosk.createOrder();
		assertThat(order.getBeverages()).hasSize(1);
		assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
	}

	@DisplayName("시간을 외부주입받게 메소드 오버로딩")
	@Test
	void createOrderWithCurrentTime() {

		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();

		cafeKiosk.add(americano);

		Order order = cafeKiosk.createOrder(LocalDateTime.of(2024,3,23,10,0));
		assertThat(order.getBeverages()).hasSize(1);
		assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
	}

	@DisplayName("영업 시간외 예외 발생 확인")
	@Test
	void createOrderOutsideOpenTime() {

		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();

		cafeKiosk.add(americano);


		assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2024,3,23,9,59)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요");

	}
}