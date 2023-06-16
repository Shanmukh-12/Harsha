package main.models.storeModels;

import java.util.List;

public class JoinedTable {

	private StoreIndentsList sil;
	private List<StoreIndentProductsList> sipl;

	public StoreIndentsList getSil() {
		return sil;
	}

	public void setSil(StoreIndentsList sil) {
		this.sil = sil;
	}

	public List<StoreIndentProductsList> getSipl() {
		return sipl;
	}

	public void setSipl(List<StoreIndentProductsList> sipl) {
		this.sipl = sipl;
	}

}
