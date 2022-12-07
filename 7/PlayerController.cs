using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour
{
    public float moveSpeed;
    public float jumpForce;

    public CharacterController controller;

    //private Vector3 moveDirection;
    private static Vector3 moveDirection;

    public float gravityScale;

    //public GameObject playerObject;


    //************************************* I think it doesnt work since to reference i need to make var static but if static it doesnt change
    /*RemoteControl control = new RemoteControl();
    Command jumpUp = new JumpUpCommand(jumpForce);
    Command jumpDown = new JumpDownCommand(-3f);*/

    // Start is called before the first frame update
    void Start()
    {
        controller = GetComponent<CharacterController>();
    }

    // Update is called once per frame
    void Update()
    {
        /*if(Input.GetButtonDown("Vertical")){
            Debug.Log("Vert");
            Debug.Log(Input.GetAxis("Vertical"));
        }
        if(Input.GetButtonDown("Horizontal")){
            Debug.Log("Hor");
            Debug.Log(Input.GetAxis("Horizontal"));
        }*/

        //control.playerMove(playerObject);

        /*MoveCommand movePlayer = new MoveCommand(Input.GetAxis("Vertical"), Input.GetAxis("Horizontal"), moveDirection.y);
        movePlayer.Execute();*/
        
        float yStore = moveDirection.y;
        moveDirection = (transform.forward * Input.GetAxis("Vertical") * moveSpeed) + (transform.right * Input.GetAxis("Horizontal") * moveSpeed);
        moveDirection = moveDirection.normalized * moveSpeed;
        moveDirection.y = yStore;

        if(controller.isGrounded){
            /*moveDirection.y = -3f;

            if(Input.GetButtonDown("Jump")){
                moveDirection.y = jumpForce;
            }*/

            if(Input.GetButtonDown("Jump")){
                /*control.setCommand(jumpUp);
                control.pressButton();*/
                
                JumpUpCommand command = new JumpUpCommand(jumpForce);
                command.Execute();
            }
            else{
                JumpDownCommand command = new JumpDownCommand(-3f);
                command.Execute();
            }
        }

        moveDirection.y += (Physics.gravity.y * Time.deltaTime * gravityScale);
        controller.Move(moveDirection * Time.deltaTime);
    }


    //****************************************** Command Pattern ******************************************
    interface Command{
        void Execute();
    }

    class JumpUpCommand : Command{
        float power;
        
        public JumpUpCommand(float jumpForce){
            power = jumpForce;
        }
        
        public void Execute(){
            moveDirection.y = power;
        }
    }

    class JumpDownCommand : Command{
        float power;
        
        public JumpDownCommand(float jumpForce){
            power = jumpForce;
        }
        
        public void Execute(){
            moveDirection.y = power;
        }
    }

    /*class MoveCommand: Command{
        float vert;
        float hori;
        float y;

        CharacterController controlPlayer;

        public MoveCommand(float Vertical, float Horizontal, float yStore){
            vert = Vertical;
            hori = Horizontal;
            y = yStore;
        }

        public void Execute(){
            moveDirection = (transform.forward * vert * moveSpeed) + (transform.right * hori * moveSpeed);
            moveDirection = moveDirection.normalized * moveSpeed;
            moveDirection.y = y;

            moveDirection.y += (Physics.gravity.y * Time.deltaTime * gravityScale);
            controller.Move(moveDirection * Time.deltaTime);
        }
    }*/

    //****************************************** RemoteControl doesnt work so fix ****************************************** without works
    class RemoteControl{
        private Command command;

        public void setCommand(Command command){
            //Debug.Log("Command: " + command);
            //Debug.Log(this);
            this.command = command;
        }
        public void pressButton(){
            command.Execute();
        }
    }
}