package ca.ualberta.cs.corgFuControllers;

import java.io.IOException;

import ca.ualberta.cs.corgFu.FavouriteListManager;
import ca.ualberta.cs.corgFu.Listener;
import ca.ualberta.cs.corgFuModels.Favourites;
import ca.ualberta.cs.corgFuModels.Question;

public class FavouritesController {

	// Lazy Singleton
	private static Favourites favList = null;

	// Warning: throws a runTimeException
	static public Favourites getFavouriteList() {
		if (favList == null) {
			try {
				favList = FavouriteListManager.getManager().loadFavouriteList();
				favList.addListener(new Listener() {
					@Override
					public void update() {
						saveFavouriteList();
					}
				});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(
						"Could not deserialize FavList from FavListManager");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(
						"Could not deserialize FavList from FavListManager");
			}
		}
		return favList;
	}

	static public void saveFavouriteList() {
		try {
			FavouriteListManager.getManager().managerSaveFavList(
					getFavouriteList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(
					"Could not deserialize StudentList from StudentListManager");
		}
	}

	public void addFavourites(Question q) {
		getFavouriteList().addFavourite(q);
	}
	

}
