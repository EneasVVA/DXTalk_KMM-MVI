//
//  ProfileView.swift
//  iosApp
//
//  Created by Eneas on 27/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class ProfileProxy: ObservableObject, Proxy {
    var viewModel: ProfileViewModel = ProfileViewModel()
    
    @Published var message: String = ""
    
    var viewState : ((ProfileViewModel) -> Void)? = nil
    
    
    func attach() {
        observe()
        viewModel.attach()
    }
    
    func detach() {
        viewModel.detach()
    }
    
    func observe() {
        viewModel.observe(viewModel.viewState) { newState in
            let state = newState as! ProfileViewState
            self.message = state.welcomeMessage
        }
    }
}

struct ProfileView: View {
    @StateObject var proxy : ProfileProxy = ProfileProxy()

    var body: some View {
        Text(proxy.message)
            .onAppear {
                proxy.attach()
            }
            .onDisappear {
                proxy.detach()
            }
    }
}

struct ProfileView_Previews: PreviewProvider {
    static var previews: some View {
        ProfileView()
    }
}
