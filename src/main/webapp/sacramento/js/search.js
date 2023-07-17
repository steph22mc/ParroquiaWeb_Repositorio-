/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


const toggleSearch = (search, button) =>{
    const searchBar = document.getElementById(search),
        searchButton = document.getElementById(button)

        searchButton.addEventListener('click', () =>{
            searchBar.classList.toggle('show-search')
        })
}

toggleSearch('search-bar', 'search-button')