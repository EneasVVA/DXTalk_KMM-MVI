import SwiftUI
import shared

class LoginProxy: ObservableObject, Proxy {
    var viewModel: LoginViewModel = LoginViewModel()
    
    @Published var email: String = ""
    @Published var password: String = ""
    @Published var completed: Bool = false
    @Published var showProgressBar: Bool = false
    @Published var emailError: String?
    @Published var passwordError: String?
    
    func attach() {
        observe()
        viewModel.attach()
    }
    
    func detach() {
        viewModel.detach()
    }
    
    func observe() {
        viewModel.observe(viewModel.viewState) { newState in
            let state = newState as! LoginViewState
            self.email = state.email
            self.password = state.password
            self.completed = state.completed
            self.showProgressBar = state.showProgressBar
            self.emailError = state.emailError
            self.passwordError = state.passwordError
        }
    }
}

struct LoginView: View {
    @StateObject var proxy : LoginProxy = LoginProxy()

    var body: some View {
        VStack {
            CustomTextField(defaultTextField: "Email", stringTextField: $proxy.email) { email in
                    proxy.viewModel.emailChanged(newEmail: email)
            }
            if let _emailError = proxy.emailError {
                Text(_emailError)
            }
            CustomSecureTextField(defaultTextField: "Password", stringTextField: $proxy.password) { password in
                    proxy.viewModel.passwordChanged(newPassword: password)
            }
            if let _passwordError = proxy.passwordError {
                Text(_passwordError)
            }
            if(proxy.showProgressBar) {
                ProgressView()
            }
            Button("SIGN ON") {
                proxy.viewModel.signInButtonClicked()
            }
        }.onAppear() {
            proxy.attach()
        }.onDisappear {
            proxy.detach()
        }.background(
            NavigationLink(
                destination: ProfileView(),
                isActive: $proxy.completed,
                label: {
                    EmptyView()
                })
        )
    }
}


struct LoginView_Previews: PreviewProvider {
	static var previews: some View {
		LoginView()
	}
}
